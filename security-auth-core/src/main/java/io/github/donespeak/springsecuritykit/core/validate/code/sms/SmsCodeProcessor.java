package io.github.donespeak.springsecuritykit.core.validate.code.sms;

import io.github.donespeak.springsecuritykit.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import io.github.donespeak.springsecuritykit.core.validate.code.AbstractValidateCodeProcessor;
import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCode;
import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCodeGenerator;
import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCodeType;

/**
 * @author Yang Guanrong
 * @date 2020/04/06 22:39
 */
@Component
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

	@Autowired
	private SmsCodeGenerator smsCodeGenerator;

	@Autowired
	private SmsCodeSender smsCodeSender;

    @Override
    protected ValidateCodeGenerator getValidateCodeGenerator() {
        return smsCodeGenerator;
    }

    @Override
    public ValidateCodeType getValidateCodeType() {
        return ValidateCodeType.SMS;
    }

	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
    	// TODO 允许配置这个参数名
		String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
		// 获取请求中的手机号码参数
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		smsCodeSender.send(mobile, validateCode.getCode());
	}
}
