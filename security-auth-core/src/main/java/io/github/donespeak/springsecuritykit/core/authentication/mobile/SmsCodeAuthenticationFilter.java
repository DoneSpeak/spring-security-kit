package io.github.donespeak.springsecuritykit.core.authentication.mobile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.donespeak.springsecuritykit.core.properties.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestUtils;

/**
 * 参考: {@link org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter}
 *
 * 手机号码认证
 *
 * @author Yang Guanrong
 * @date 2020/04/06 23:22
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private boolean postOnly = true;
	private String mobileParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;

	public SmsCodeAuthenticationFilter() {
		// TODO 允许自定义URL
		super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, "POST"));
	}

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException, IOException, ServletException {
	    if (postOnly && !request.getMethod().equals("POST")) {
		    throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
	    }

	    String mobile = obtainMobile(request);
	    if(mobile == null) {
			mobile = "";
	    }
	    mobile = mobile.trim();

	    SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);

	    setDetails(request, authRequest);

	    return this.getAuthenticationManager().authenticate(authRequest);
    }

	private String obtainMobile(HttpServletRequest request) {
		return request.getParameter(mobileParameter);
	}

	protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	public void setMobileParameter(String mobileParameter) {
		Assert.hasText(mobileParameter, "Mobile parameter must not be empty or null");
		this.mobileParameter = mobileParameter;
	}

	public String getMobileParameter() {
		return mobileParameter;
	}
}
