package io.github.donespeak.springsecuritykit.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * @author Yang Guanrong
 * @date 2020/04/05 01:22
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(SecurityCoreConfig.class)
public @interface EnableSecurityKitCore {
}
