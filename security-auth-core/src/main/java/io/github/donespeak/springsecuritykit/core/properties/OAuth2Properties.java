package io.github.donespeak.springsecuritykit.core.properties;

import lombok.Data;

/**
 * @author Yang Guanrong
 * @date 2020/01/12 20:33
 */
@Data
public class OAuth2Properties {
    /**
     * jwt的token私钥
     */
    private String jwtSigningKey;

    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};

    @Data
    public static class OAuth2ClientProperties {
        private String clientId;
        private String clientSecret;
        private int accessTokenValidateSeconds;
    }
}
