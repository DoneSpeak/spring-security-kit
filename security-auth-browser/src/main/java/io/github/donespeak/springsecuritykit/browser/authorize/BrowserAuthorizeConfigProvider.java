package io.github.donespeak.springsecuritykit.browser.authorize;

import io.github.donespeak.springsecuritykit.core.authorize.AuthorizeConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author Yang Guanrong
 * @date 2020/04/05 01:30
 */
@Component
public class BrowserAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		return false;
	}
}
