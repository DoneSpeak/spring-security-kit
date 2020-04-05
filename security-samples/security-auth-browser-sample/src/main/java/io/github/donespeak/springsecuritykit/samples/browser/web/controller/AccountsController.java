package io.github.donespeak.springsecuritykit.samples.browser.web.controller;

import io.github.donespeak.springsecuritykit.samples.browser.entity.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Yang Guanrong
 * @date 2020/04/05 14:45
 */
@RestController
@RequestMapping("/accounts")
public class AccountsController {

	@GetMapping("")
	public List<Account> listAccounts() {
		return Arrays.asList(new Account());
	}

	@GetMapping("/{id:\\d+}")
	public Account getAccoutn(long id) {
		Account account = new Account();
		account.setId(id);
		return account;
	}
}
