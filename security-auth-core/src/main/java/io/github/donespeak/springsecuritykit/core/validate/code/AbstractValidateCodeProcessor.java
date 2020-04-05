package io.github.donespeak.springsecuritykit.core.validate.code;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Yang Guanrong
 * @date 2020/04/06 00:02
 */
public abstract class AbstractValidateCodeProcessor<T extends ValidateCode> implements ValidateCodeProcessor {

    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        T validateCode = (T) getValidateCodeGenerator().generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    private void save(ServletWebRequest request, ValidateCode validateCode) {
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        validateCodeRepository.save(request, code, getValidateCodeType());
    }

    @Override
    public void validate(ServletWebRequest request) {
        ValidateCodeType codeType = getValidateCodeType();
        ValidateCode validateCode = validateCodeRepository.get(request, codeType);
        String codeInRequest = "";
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("Fail to get validate code.", e);
        }
        if(StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(codeType + " validate code shouldn't be blank.");
        }
        if(validateCode == null) {
            throw new ValidateCodeException(codeType + " validate code doesn't exist.");
        }
        if(validateCode.isExpried()) {
            validateCodeRepository.remove(request, codeType);
            throw new ValidateCodeException(codeType + " validate code is expired.");
        }
        if(!StringUtils.equals(validateCode.getCode(), codeInRequest)) {
            throw new ValidateCodeException(codeType + " validate codes don't match.");
        }
        // 验证成功，移除缓存
        validateCodeRepository.remove(request, codeType);
    }

    protected abstract ValidateCodeGenerator getValidateCodeGenerator();

    protected abstract void send(ServletWebRequest request, T validateCode) throws Exception;
}
