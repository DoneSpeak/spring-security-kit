package io.github.donespeak.springsecuritykit.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.donespeak.springsecuritykit.browser.support.SimpleResponse;
import io.github.donespeak.springsecuritykit.core.properties.SecurityProperties;
import io.github.donespeak.springsecuritykit.core.properties.SingInResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 认证失败的时候的处理
 *
 * @author Yang Guanrong
 * @date 2020/04/05 01:27
 */
@Slf4j
@Component("kitAuthenticationFailureHandler")
public class KitAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {

        log.info("Fail to authenticate.");
        if (SingInResponseType.JSON.equals(securityProperties.getBrowser().getSignInResponseType())) {
            // 返回json结果
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            // TODO 让返回的json数据可以被自定义
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
