package io.github.donespeak.springsecuritykit.core.properties;

import lombok.Data;

/**
 * Browser环境配置项
 *
 * @author Yang Guanrong
 * @date 2020/04/04 22:18
 */
@Data
public class BrowserProperties {
	/**
	 * 登录页面
	 */
	private String signInPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;
	private String signInSuccessUrl = "";
	/**
	 * 认证成功之后的响应类型
	 */
	private SingInResponseType signInResponseType = SingInResponseType.JSON;
}
