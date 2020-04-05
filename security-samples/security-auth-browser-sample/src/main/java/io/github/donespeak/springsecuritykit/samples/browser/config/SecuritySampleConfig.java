package io.github.donespeak.springsecuritykit.samples.browser.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author Yang Guanrong
 * @date 2020/04/05 13:59
 */
@Configuration
public class SecuritySampleConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return StringUtils.equals(rawPassword, encodedPassword);
            }
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        String username = "theName";
        String password = "thePassword";
        UserDetails user = User.withUsername(username).password(password).authorities("ROLE_USER").build();
        return new InMemoryUserDetailsManager(user);
    }
}
