package io.github.donespeak.springsecuritykit.app.oauth;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author Yang Guanrong
 * @date 2020/01/12 20:27
 */
@Configuration
public class TokenStoreConfig {

    @Configuration
    public static class JwtTokenStoreConfig {

        @Bean
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey("jinwandalaohu");
            return converter;
        }

        @Bean
        @ConditionalOnMissingBean(TokenEnhancer.class)
        public JwtTokenEnhancer jwtTokenEnhancer() {
            return new JwtTokenEnhancer();
        }
    }
}
