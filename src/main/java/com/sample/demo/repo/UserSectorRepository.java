package com.sample.demo.repo;

import com.sample.demo.model.UserSector;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSectorRepository extends CrudRepository<UserSector, Long> {

}