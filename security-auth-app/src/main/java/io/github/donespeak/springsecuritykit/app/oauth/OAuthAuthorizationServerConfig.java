package io.github.donespeak.springsecuritykit.app.oauth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author Yang Guanrong
 * @date 2020/01/12 20:09
 */
@Configuration
@EnableAuthorizationServer
public class OAuthAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    // @Autowired
    // private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * tokenKey的访问权限表达式配置
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //允许表单认证
        oauthServer.allowFormAuthenticationForClients();
        // TODO 为什么没有生效？
        // security.tokenKeyAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        String finalSecret = passwordEncoder.encode("123456");
        // builder.withClient("app")
        // .secret(secret)
        // .authorizedGrantTypes("password", "refresh_token")
        // .accessTokenValiditySeconds(7200)
        // .refreshTokenValiditySeconds(7200)
        // .scopes("all");
        // 配置两个客户端,一个用于password认证一个用于client认证
        clients.inMemory().withClient("client_1").resourceIds(OAuthResourceServerConfig.DEMO_RESOURCE_ID)
            .authorizedGrantTypes("client_credentials", "refresh_token").scopes("select").authorities("oauth2")
            .secret(finalSecret).and().withClient("client_2").resourceIds(OAuthResourceServerConfig.DEMO_RESOURCE_ID)
            .authorizedGrantTypes("password", "refresh_token")
            .scopes("select").authorities("oauth2").secret(finalSecret);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            // .tokenStore(new RedisTokenStore(redisConnectionFactory))
            .tokenStore(tokenStore)
            .authenticationManager(authenticationManager)
            // .userDetailsService(userDetailsService)
            // 如果不增加如下的配置，会放回请求方法不支持的异常
            .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

        if(jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancerList = new ArrayList<>();
            enhancerList.add(jwtAccessTokenConverter);
            enhancerList.add(jwtTokenEnhancer);
            tokenEnhancerChain.setTokenEnhancers(enhancerList);
            endpoints.tokenEnhancer(tokenEnhancerChain).accessTokenConverter(jwtAccessTokenConverter);
        }
    }
}
