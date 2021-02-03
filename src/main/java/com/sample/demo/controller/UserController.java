package com.sample.demo.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.demo.domain.UserResource;
import com.sample.demo.usecases.CreateUserResources;
import com.sample.demo.usecases.ProcessUserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(value = "/users")
@CrossOrigin
@AllArgsConstructor
public class UserController {

    private ProcessUserRequest processUserRequest;
    private CreateUserResources createUserResources;

    @GetMapping
    @ResponseBody
    public List<UserResource> users() {
        return createUserResources.execute();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<HttpStatus> save(@RequestBody String userRequestAsString)
            throws JsonMappingException, JsonProcessingException {
        UserResource userRequest = new ObjectMapper().readValue(userRequestAsString, UserResource.class);
        processUserRequest.execute(userRequest);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

}
