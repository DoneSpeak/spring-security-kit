package io.github.donespeak.springsecuritykit.samples.browser;

import io.github.donespeak.springsecuritykit.browser.EnableSecurityKitBrowserSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSecurityKitBrowserSecurity
public class SecurityAuthBrowserSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityAuthBrowserSampleApplication.class, args);
	}

}
