package io.github.donespeak.springsecuritykit.samples.browser.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Yang Guanrong
 * @date 2020/04/06 11:12
 */
@RestController
@RequestMapping("/phones")
public class PhoneController {

	@PostMapping
	public Object postPhone(@RequestBody Map<String, Object> input) {
		return input;
	}
}
