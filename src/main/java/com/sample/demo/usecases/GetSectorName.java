package com.sample.demo.usecases;

import com.sample.demo.model.LevelFourSector;
import com.sample.demo.model.UserSector;
import com.sample.demo.repo.LevelFourSectorRepository;
import com.sample.demo.repo.LevelThreeSectorRepository;
import com.sample.demo.repo.LevelTwoSectorRepository;
import com.sample.demo.repo.MainSectorRepository;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetSectorName {
    private MainSectorRepository mainSectorRepository;
    private LevelTwoSectorRepository levelTwoSectorRepository;
    private LevelThreeSectorRepository levelThreeSectorRepository;
    private LevelFourSectorRepository levelFourSectorRepository;

    public String execute(@Validated UserSector userSector) {
        int sectorId = userSector.getSectorId();
        switch (userSector.getSectorLevel()) {
            case 0:
                return getMainSectorName(sectorId);
            case 1:
                return getLevelTwoSectorName(sectorId);
            case 2:
                return getLevelThreeSectorName(sectorId);
            case 3:
                return getLevelFourSectorName(sectorId);
            default:
                throw new IllegalArgumentException();
        }
    }

    private String getMainSectorName(int sectorId) {
        return mainSectorRepository.findByMainSectorId(sectorId).getName();
    }

    private String getLevelTwoSectorName(int sectorId) {
        return levelTwoSectorRepository.findByLevelTwoSectorId(sectorId).getName();
    }

    private String getLevelThreeSectorName(int sectorId) {
        return levelThreeSectorRepository.findByLevelThreeSectorId(sectorId).getName();
    }

    private String getLevelFourSectorName(int sectorId) {
        return levelFourSectorRepository.findByLevelFourSectorId(sectorId).getName();
    }
}
