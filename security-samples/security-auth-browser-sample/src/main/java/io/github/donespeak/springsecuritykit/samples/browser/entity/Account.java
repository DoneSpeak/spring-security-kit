package io.github.donespeak.springsecuritykit.samples.browser.entity;

import lombok.Data;

/**
 * @author Yang Guanrong
 * @date 2020/04/05 14:44
 */
@Data
public class Account {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String password;
}