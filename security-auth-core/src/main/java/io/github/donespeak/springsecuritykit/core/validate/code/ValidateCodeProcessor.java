package io.github.donespeak.springsecuritykit.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Yang Guanrong
 * @date 2020/04/06 00:00
 */
public interface ValidateCodeProcessor {
	/**
	 * 创建验证码
	 */
	void create(ServletWebRequest request) throws Exception;

	/**
	 * 校验验证码
	 */
	void validate(ServletWebRequest request);

	ValidateCodeType getValidateCodeType();
}
