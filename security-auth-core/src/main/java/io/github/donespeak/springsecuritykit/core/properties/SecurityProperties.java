package io.github.donespeak.springsecuritykit.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Yang Guanrong
 * @date 2020/01/12 20:31
 */
@Data
@ConfigurationProperties(prefix = "dspk.security-kit")
public class SecurityProperties {

    /**
     * OAuth2 认证配置
     */
    private OAuth2Properties oauth2 = new OAuth2Properties();
}
