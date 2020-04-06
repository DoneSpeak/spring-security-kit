package io.github.donespeak.springsecuritykit.core.properties;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 20:38
 */
public interface SecurityConstants {

	/**
	 * 当请求需要身份认证时，默认跳转到的url
	 */
	String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

	/**
	 * 用户名密码登录认证请求的url
	 */
	String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/authentication/form";
	/**
	 * 登录页面配置
	 */
	String DEFAULT_SIGN_IN_PAGE_URL = "/signIn.html";

	/**
	 * 获取验证码接口的前缀
	 */
	String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";
	/**
	 * 验证图片验证码时，http请求中默认携带的图片验证码信息的参数名称
	 */
	String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

	/**
	 * 验证短信验证码时，http请求中默认携带的短信验证码信息的参数名称
	 */
	String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
	/**
	 * 发送短信验证码和验证短信验证码时用的手机号码参数名
	 */
	String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
}
