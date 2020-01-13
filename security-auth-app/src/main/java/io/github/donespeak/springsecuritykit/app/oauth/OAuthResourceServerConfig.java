package io.github.donespeak.springsecuritykit.app.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author Yang Guanrong
 * @date 2020/01/12 20:10
 */
@Configuration
@EnableResourceServer
public class OAuthResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String DEMO_RESOURCE_ID = "order";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/order/**").authenticated();//配置order访问控制，必须认证过后才可以访问

    }
}
