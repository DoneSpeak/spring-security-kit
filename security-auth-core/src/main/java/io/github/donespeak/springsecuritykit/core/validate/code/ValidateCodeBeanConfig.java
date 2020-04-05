package io.github.donespeak.springsecuritykit.core.validate.code;

import io.github.donespeak.springsecuritykit.core.validate.code.image.ImageCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yang Guanrong
 * @date 2020/04/05 17:21
 */
@Configuration
public class ValidateCodeBeanConfig {

	@Bean("imageCodeGenerator")
	public ImageCodeGenerator imageCodeGenerator() {
		return new ImageCodeGenerator(50, 30, 6, 60);
	}
}
