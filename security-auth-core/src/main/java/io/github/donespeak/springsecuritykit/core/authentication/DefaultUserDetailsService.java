package io.github.donespeak.springsecuritykit.core.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Yang Guanrong
 * @date 2020/02/04 00:38
 */
public class DefaultUserDetailsService implements UserDetailsService {

    private Logger log = LoggerFactory.getLogger(getClass());

    public DefaultUserDetailsService() {
        String message = "A Customized UserDetailsService is required.";
        throw new AssertionError(message);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("username `" + username + "` not found.");
    }
}
