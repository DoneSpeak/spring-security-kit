package io.github.donespeak.springsecuritykit.samples.app.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang Guanrong
 * @date 2020/02/04 10:00
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping("")
    public Object getAccount() {
        // 获取当前账户信息
        return null;
    }
}
