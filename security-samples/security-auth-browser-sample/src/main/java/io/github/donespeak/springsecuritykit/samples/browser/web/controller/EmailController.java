package io.github.donespeak.springsecuritykit.samples.browser.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Yang Guanrong
 * @date 2020/04/06 11:12
 */
@RestController
@RequestMapping("/emails")
public class EmailController {

	@PostMapping("")
	public Object postEmail(Map<String, Object> input) {
		return input;
	}
}
