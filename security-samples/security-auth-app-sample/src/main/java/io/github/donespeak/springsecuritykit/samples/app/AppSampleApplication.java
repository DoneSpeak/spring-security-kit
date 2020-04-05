package io.github.donespeak.springsecuritykit.samples.app;

import io.github.donespeak.springsecuritykit.app.EnableSecurityKitApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Yang Guanrong
 * @date 2020/02/04 09:45
 */
@SpringBootApplication
@RestController
@EnableSwagger2
@EnableSecurityKitApp
public class AppSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppSampleApplication.class, args);
    }
}
