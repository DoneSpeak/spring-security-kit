package io.github.donespeak.springsecuritykit.core.authorize;

import io.github.donespeak.springsecuritykit.core.properties.SecurityConstants;
import io.github.donespeak.springsecuritykit.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author Yang Guanrong
 * @date 2020/04/05 01:54
 */
@Component
public class BasicAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
		registry.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
			securityProperties.getBrowser().getSignInPage(),
			SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*").permitAll();
        return false;
    }
}
