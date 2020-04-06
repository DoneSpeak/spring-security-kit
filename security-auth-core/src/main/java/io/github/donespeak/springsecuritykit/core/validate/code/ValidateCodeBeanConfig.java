package io.github.donespeak.springsecuritykit.core.validate.code;

import io.github.donespeak.springsecuritykit.core.validate.code.sms.DefaultSmsCodeSender;
import io.github.donespeak.springsecuritykit.core.validate.code.sms.SmsCodeGenerator;
import io.github.donespeak.springsecuritykit.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.donespeak.springsecuritykit.core.properties.SecurityProperties;
import io.github.donespeak.springsecuritykit.core.properties.ValidateCodeProperties;
import io.github.donespeak.springsecuritykit.core.validate.code.image.ImageCodeGenerator;

/**
 * @author Yang Guanrong
 * @date 2020/04/05 17:21
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

	@Bean("imageCodeGenerator")
	public ImageCodeGenerator imageCodeGenerator() {
        ValidateCodeProperties.ImageCodeProperties imageCodeProperties = securityProperties.getCode().getImage();
        return new ImageCodeGenerator(imageCodeProperties.getWidth(), imageCodeProperties.getHeight(),
            imageCodeProperties.getLength(), imageCodeProperties.getExpireIn());
	}

	@Bean("smsCodeGenerator")
	public SmsCodeGenerator smsCodeGenerator() {
		ValidateCodeProperties.SmsCodeProperties smsCodeProperties = securityProperties.getCode().getSms();
		return new SmsCodeGenerator(smsCodeProperties.getLength(), smsCodeProperties.getExpireIn());
	}

	/**
	 * 短信验证码发送器. 可覆盖重写。
	 */
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}
}
