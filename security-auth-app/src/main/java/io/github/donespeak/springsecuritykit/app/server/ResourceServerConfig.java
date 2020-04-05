package io.github.donespeak.springsecuritykit.app.server;

import io.github.donespeak.springsecuritykit.core.authorize.AuthorizeConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author Yang Guanrong
 * @date 2020/02/03 19:27
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * 授权配置
     */
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 认证配置

        // 账号密码登录
        // 谷歌账号登录
        // 微信账号登录
        // QQ账号登录

        // token 验证
        // 验证码验证
        http.csrf().disable();

        // 授权配置
        authorizeConfigManager.config(http.authorizeRequests());
    }
}
