package io.github.donespeak.springsecuritykit.samples.app.web.controller;

import io.github.donespeak.springsecuritykit.samples.app.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang Guanrong
 * @date 2020/02/04 09:54
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id:\\d+}")
    public User getUser(@PathVariable long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("xiaoming");
        return user;
    }
}
