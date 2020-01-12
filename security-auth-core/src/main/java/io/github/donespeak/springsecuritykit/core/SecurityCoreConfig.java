package io.github.donespeak.springsecuritykit.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import io.github.donespeak.springsecuritykit.core.properties.SecurityProperties;

/**
 * @author Yang Guanrong
 * @date 2020/01/12 20:03
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
