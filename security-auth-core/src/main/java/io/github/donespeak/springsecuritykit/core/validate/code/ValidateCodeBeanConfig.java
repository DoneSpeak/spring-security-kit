package io.github.donespeak.springsecuritykit.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
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
}
