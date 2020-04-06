package io.github.donespeak.springsecuritykit.browser;

import io.github.donespeak.springsecuritykit.core.authentication.FormAuthenticationConfig;
import io.github.donespeak.springsecuritykit.core.authorize.AuthorizeConfigManager;
import io.github.donespeak.springsecuritykit.core.properties.SecurityProperties;
import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		formAuthenticationConfig.configure(http);

		http.apply(validateCodeSecurityConfig)
				.and()
			.rememberMe()
				.tokenRepository(persistenceTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
				.and()
			.csrf().disable();

		authorizeConfigManager.config(http.authorizeRequests());
	}

	public PersistentTokenRepository persistenceTokenRepository() {
		// TODO 是否有redis的方案
		// TODO 研究一下 TokenBasedRememberMeServices 方案
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		// 启动的时候自动创建数据库表，也可以自己手动创建
		// tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
}
