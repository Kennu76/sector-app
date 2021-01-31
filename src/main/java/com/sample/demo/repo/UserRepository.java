package com.sample.demo.repo;

import com.sample.demo.model.User;

import org.springframework.data.repository.CrudRepository;

import antlr.collections.List;

public interface UserRepository extends CrudRepository<User, Long> {

}