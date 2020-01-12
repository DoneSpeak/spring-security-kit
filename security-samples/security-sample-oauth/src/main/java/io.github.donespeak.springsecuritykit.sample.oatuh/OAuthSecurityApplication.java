package io.github.donespeak.springsecuritykit.sample.oatuh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
}