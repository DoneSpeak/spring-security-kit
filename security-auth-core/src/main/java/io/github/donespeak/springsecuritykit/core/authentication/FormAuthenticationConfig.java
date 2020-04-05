package io.github.donespeak.springsecuritykit.core.authentication;

import io.github.donespeak.springsecuritykit.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author Yang Guanrong
 * @date 2020/02/04 00:29
 */
@Component
public class FormAuthenticationConfig {

    public static final String KIT_AUTHENTICATION_SUCCESS_HANDLER__BEAN_NAME = "kitAuthenticationSuccessHandler";
    public static final String KIT_AUTHENTICATION_FAILURE_HANDLER_BEAN_NAME = "kitAuthenticationFailureHandler";

    @Autowired
    @Qualifier(KIT_AUTHENTICATION_SUCCESS_HANDLER__BEAN_NAME)
    private AuthenticationSuccessHandler kitAuthenticationSuccessHandler;

    @Autowired
    @Qualifier(KIT_AUTHENTICATION_FAILURE_HANDLER_BEAN_NAME)
    private AuthenticationFailureHandler kitAuthenticationFailureHandler;

    public void configure(HttpSecurity http) throws Exception {
        // 表单登录配置
        // TODO 如果配置了表单登录，那么还可以配置HttpBasic认证吗？
        // TODO 如何想Spring Security OAuth 2.0 那样允许修改这些路径？
        http.formLogin()
            .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
            .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
            .successHandler(kitAuthenticationSuccessHandler)
            .failureHandler(kitAuthenticationFailureHandler);
    }
}
