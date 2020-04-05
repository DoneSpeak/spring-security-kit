package io.github.donespeak.springsecuritykit.browser.controller;

import io.github.donespeak.springsecuritykit.browser.support.SimpleResponse;
import io.github.donespeak.springsecuritykit.core.properties.SecurityConstants;
import io.github.donespeak.springsecuritykit.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 21:42
 */
@Slf4j
@RestController
public class BrowserSecurityController {

	/**
	 * formLogin()中，如果认证失败发生跳转的时候，会把请求保存到这个RequestCache 中。
	 */
	private RequestCache requestCache = new HttpSessionRequestCache();

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private SecurityProperties securityProperties;

	@RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public Object requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if(savedRequest != null) {
			// 来自browser页面的请求
			String targetUrl = savedRequest.getRedirectUrl();
			if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
				// 判断是否来自html页面的请求
				// 跳转回到引发跳转的html页面
				redirectStrategy.sendRedirect(request, response, getSignInPage());
			}
		}
		return new SimpleResponse("认证未通过");
	}

	private String getSignInPage() {
		return securityProperties.getBrowser().getSignInPage();
	}
}
