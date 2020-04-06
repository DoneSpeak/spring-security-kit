package io.github.donespeak.springsecuritykit.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Yang Guanrong
 * @date 2020/04/06 23:34
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    	SmsCodeAuthenticationToken authToken = (SmsCodeAuthenticationToken) authentication;
		UserDetails user = userDetailsService.loadUserByUsername((String) authToken.getPrincipal());

		if(user == null) {
			throw new InternalAuthenticationServiceException("Unable to obtain user information.");
		}

		SmsCodeAuthenticationToken authResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());

	    authResult.setDetails(authToken.getDetails());
        return authResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

    public void setUserDetailsService(UserDetailsService userDetailsService) {
    	this.userDetailsService = userDetailsService;
    }
}
