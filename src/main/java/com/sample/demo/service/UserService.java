package com.sample.demo.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sample.demo.model.User;
import com.sample.demo.repo.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return StreamSupport.stream
        (userRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
    }

}
