package com.sample.demo.repo;

import com.sample.demo.model.MainSector;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainSectorRepository extends CrudRepository<MainSector, Long> {

}