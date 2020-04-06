package io.github.donespeak.springsecuritykit.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Yang Guanrong
 * @date 2020/04/06 00:07
 */
public interface ValidateCodeRepository {

	/**
	 * 保存验证码
	 */
	void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type);

	/**
	 * 获取验证码
	 */
	ValidateCode get(ServletWebRequest request, ValidateCodeType type);

	/**
	 * 移除验证码
	 */
	void remove(ServletWebRequest request, ValidateCodeType type);
}
