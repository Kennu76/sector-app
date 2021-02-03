package com.sample.demo.repo;

import com.sample.demo.model.LevelTwoSector;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelTwoSectorRepository extends CrudRepository<LevelTwoSector, Long> {

    LevelTwoSector findByLevelTwoSectorId(int sectorId);

}