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
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

/**
 * 认证成功之后的处理.
 *
 * SavedRequestAwareAuthenticationSuccessHandler 为默认处理器。成功之后会跳转到之前的页面。
 *
 * @author Yang Guanrong
 * @date 2020/04/05 01:26
 */
@Slf4j
@Component("kitAuthenticationSuccessHandler")
public class KitAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private ObjectMapper objectMapper;

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws ServletException, IOException {
		log.info("Authentication success");

		if (SingInResponseType.JSON.equals(securityProperties.getBrowser().getSignInResponseType())) {
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			String type = authentication.getClass().getSimpleName();
			// TODO 让返回结构可以定制化
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(type)));
		} else {
			// 判断是否配置有认证成功之后的跳转路径，有则使用配置路径
			// 没有配置默认跳转，使用默认的条状策略，为优先跳转到原页面，无原页面再跳转到根路径
			if(StringUtils.isNotBlank(securityProperties.getBrowser().getSignInSuccessUrl())) {
				requestCache.removeRequest(request, response);
				// 使用默认目标路径
				setAlwaysUseDefaultTargetUrl(true);
				// 设置跳转默认路径
				setDefaultTargetUrl(securityProperties.getBrowser().getSignInSuccessUrl());
			}
			// 使用默认的跳转策略
			super.onAuthenticationSuccess(request, response, authentication);
		}
    }
}
