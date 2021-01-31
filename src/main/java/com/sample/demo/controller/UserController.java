package com.sample.demo.controller;

import java.util.List;

import com.sample.demo.model.User;
import com.sample.demo.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;
    
    @GetMapping
    public List<User> getUsers() {
        return userService.findAllUsers();
    }
    
}
