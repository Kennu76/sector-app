package com.sample.demo.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.demo.domain.UserRequest;
import com.sample.demo.model.User;
import com.sample.demo.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController {
    private UserService userService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public List<User> getUsers() {
        return userService.findAllUsers();
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody String userRequestAsString) throws JsonMappingException, JsonProcessingException {
        UserRequest userRequest = objectMapper.readValue(userRequestAsString, UserRequest.class);
        System.out.println("got et" + userRequest.toString());
        // userRepository.save(user);
    }



}
