package io.github.donespeak.springsecuritykit.sample.oatuh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Yang Guanrong
 * @date 2020/01/12 21:06
 */
@SpringBootApplication
@RestController
@EnableSwagger2
@ComponentScan("io.github.donespeak.springsecuritykit")
public class OAuthSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthSecurityApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }

    @GetMapping("/order/{orderId}")
    public Map<String, String> order(@PathVariable String orderId) {
        Map<String, String> order = new HashMap<>();
        order.put("orderId", orderId);
        return order;
    }

    @GetMapping("auth")
    public Authentication getAuth(Authentication authentication) {
        if(!(authentication instanceof OAuth2Authentication)) {
            return authentication;
        }
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
        Set<String> scopes = oAuth2Request.getScope();
        return authentication;
    }
}