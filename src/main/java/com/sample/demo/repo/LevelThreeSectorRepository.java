package com.sample.demo.repo;

import com.sample.demo.model.LevelThreeSector;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelThreeSectorRepository extends CrudRepository<LevelThreeSector, Long> {

    LevelThreeSector findByLevelThreeSectorId(int sectorId);

}