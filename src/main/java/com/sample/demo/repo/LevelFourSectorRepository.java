package com.sample.demo.repo;

import com.sample.demo.model.LevelFourSector;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelFourSectorRepository extends CrudRepository<LevelFourSector, Long> {

    LevelFourSector findByLevelFourSectorId(int sectorId);
}