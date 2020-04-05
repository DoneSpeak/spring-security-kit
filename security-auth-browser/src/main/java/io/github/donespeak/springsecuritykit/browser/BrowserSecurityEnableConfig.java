package io.github.donespeak.springsecuritykit.browser;

import io.github.donespeak.springsecuritykit.core.EnableSecurityKitCore;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Yang Guanrong
 * @date 2020/04/05 01:23
 */
@EnableSecurityKitCore
@ComponentScan(basePackageClasses = BrowserSecurityConfig.class)
public class BrowserSecurityEnableConfig {
}
