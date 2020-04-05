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

	String DEFAULT_SIGN_IN_PAGE_URL = "/signIn.html";
}
