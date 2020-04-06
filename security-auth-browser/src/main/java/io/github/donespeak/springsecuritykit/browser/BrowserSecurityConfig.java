package io.github.donespeak.springsecuritykit.browser;

import io.github.donespeak.springsecuritykit.core.authentication.FormAuthenticationConfig;
import io.github.donespeak.springsecuritykit.core.authorize.AuthorizeConfigManager;
import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Yang Guanrong
 * @date 2020/04/05 01:08
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private FormAuthenticationConfig formAuthenticationConfig;

	@Autowired
	private AuthorizeConfigManager authorizeConfigManager;

	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		formAuthenticationConfig.configure(http);

		http.apply(validateCodeSecurityConfig)
				.and()
			.csrf().disable();

		authorizeConfigManager.config(http.authorizeRequests());
	}
}
