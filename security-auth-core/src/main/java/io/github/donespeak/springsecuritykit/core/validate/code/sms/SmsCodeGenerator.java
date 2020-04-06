package io.github.donespeak.springsecuritykit.core.validate.code.sms;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCode;
import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCodeGenerator;

/**
 * @author Yang Guanrong
 * @date 2020/04/06 22:35
 */
public class SmsCodeGenerator implements ValidateCodeGenerator {

	private int codeLength;
	private int expireIn;

	public SmsCodeGenerator(int codeLength, int expireIn) {
		this.codeLength = codeLength;
		this.expireIn = expireIn;
	}

    @Override
    public ValidateCode generate(ServletWebRequest request) {
	    String code = RandomStringUtils.randomNumeric(codeLength);
	    return new ValidateCode(code, expireIn);
    }
}
