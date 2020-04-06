package io.github.donespeak.springsecuritykit.core.validate.code;

import io.github.donespeak.springsecuritykit.core.properties.SecurityConstants;

/**
 * 验证类型
 *
 * @author Yang Guanrong
 * @date 2020/04/05 23:24
 */
public enum ValidateCodeType {
	/**
	 * 通过短信验证
	 */
	SMS(SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS),
	/**
	 * 通过图片验证
	 */
	IMAGE(SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE)
	;

	/**
	 * http请求中的验证码信息参数名
	 */
	private final String paramNameOnValidate;

	ValidateCodeType(String paramNameOnValidate) {
		this.paramNameOnValidate = paramNameOnValidate;
	}

	public String getParamNameOnValidate() {
		return paramNameOnValidate;
	}
}
