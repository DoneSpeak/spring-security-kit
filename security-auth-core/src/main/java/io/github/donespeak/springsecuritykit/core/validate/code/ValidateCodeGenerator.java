package io.github.donespeak.springsecuritykit.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成接口
 *
 * @author Yang Guanrong
 * @date 2020/04/05 16:45
 */
public interface ValidateCodeGenerator {
	/**
	 * 生成验证码
	 */
	ValidateCode generate(ServletWebRequest request);
}
