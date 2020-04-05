package io.github.donespeak.springsecuritykit.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Yang Guanrong
 * @date 2020/01/12 20:31
 */
@Data
@ConfigurationProperties(prefix = "dspk.securitykit")
public class SecurityProperties {

    /**
     * 浏览器配置配置项
     */
    private BrowserProperties browser = new BrowserProperties();

    /**
     * 认证码相关的配置
     */
    private ValidateCodeProperties validate = new ValidateCodeProperties();

    /**
     * OAuth2 认证配置
     */
    private OAuth2Properties oauth2 = new OAuth2Properties();

    /**
     * 社交账号相关的配置
     */
    private SocialProperties social = new SocialProperties();
}
