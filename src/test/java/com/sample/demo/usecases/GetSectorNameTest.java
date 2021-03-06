package com.sample.demo.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.sample.demo.model.LevelFourSector;
import com.sample.demo.model.LevelThreeSector;
import com.sample.demo.model.LevelTwoSector;
import com.sample.demo.model.MainSector;
import com.sample.demo.model.UserSector;
import com.sample.demo.repo.LevelFourSectorRepository;
import com.sample.demo.repo.LevelThreeSectorRepository;
import com.sample.demo.repo.LevelTwoSectorRepository;
import com.sample.demo.repo.MainSectorRepository;
import com.sample.demo.test_util.GenerateClasses;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetSectorNameTest {

    private static final String MAIN_SECTOR_NAME = "mainSectorName";
    private static final String LEVEL_TWO_SECTOR_NAME = "levelTwoSectorName";
    private static final String LEVEL_THREE_SECTOR_NAME = "levelThreeSectorName";
    private static final String LEVEL_FOUR_SECTOR_NAME = "levelFourSectorName";

    @Mock
    GetSectorName getSectorName;

    @Mock
    MainSectorRepository mainSectorRepository;

    @Mock
    LevelTwoSectorRepository levelTwoSectorRepository;

    @Mock
    LevelThreeSectorRepository levelThreeSectorRepository;

    @Mock
    LevelFourSectorRepository levelFourSectorRepository;

    @Test
    public void whenLevelZero_thenReturnsMainSectorName() {
        UserSector userSector = GenerateClasses.createUserSector();
        userSector.setSectorLevel(0);

        mockMainSectorRepo();

        assertEquals(MAIN_SECTOR_NAME, getSectorName.execute(userSector));
    }

    @Test
    public void whenLevelOne_thenReturnsLevelTwoSectorName() {
        UserSector userSector = GenerateClasses.createUserSector();
        userSector.setSectorLevel(1);

        mockLevelTwoSectorRepo();

        assertEquals(LEVEL_TWO_SECTOR_NAME, getSectorName.execute(userSector));
    }

    @Test
    public void whenLevelTwo_thenReturnsLevelThreeSectorName() {
        UserSector userSector = GenerateClasses.createUserSector();
        userSector.setSectorLevel(2);

        mockLevelThreeSectorRepo();

        assertEquals(LEVEL_THREE_SECTOR_NAME, getSectorName.execute(userSector));
    }

    @Test
    public void whenLevelThree_thenReturnsLevelFourSectorName() {
        UserSector userSector = GenerateClasses.createUserSector();
        userSector.setSectorLevel(3);

        mockLevelFourSectorRepo();

        assertEquals(LEVEL_FOUR_SECTOR_NAME, getSectorName.execute(userSector));
    }

    private void mockMainSectorRepo() {
        MainSector mainSector = new MainSector();
        mainSector.setName(MAIN_SECTOR_NAME);
        when(mainSectorRepository.findByMainSectorId(GenerateClasses.SECTOR_ID)).thenReturn(mainSector);
    }

    private void mockLevelTwoSectorRepo() {
        LevelTwoSector levelTwoSector = new LevelTwoSector();
        levelTwoSector.setName(LEVEL_TWO_SECTOR_NAME);
        when(levelTwoSectorRepository.findByLevelTwoSectorId(GenerateClasses.SECTOR_ID)).thenReturn(levelTwoSector);
    }

    private void mockLevelThreeSectorRepo() {
        LevelThreeSector levelThreeSector = new LevelThreeSector();
        levelThreeSector.setName(LEVEL_THREE_SECTOR_NAME);
        when(levelThreeSectorRepository.findByLevelThreeSectorId(GenerateClasses.SECTOR_ID))
                .thenReturn(levelThreeSector);
    }

    private void mockLevelFourSectorRepo() {
        LevelFourSector levelFourSector = new LevelFourSector();
        levelFourSector.setName(LEVEL_FOUR_SECTOR_NAME);
        when(levelFourSectorRepository.findByLevelFourSectorId(GenerateClasses.SECTOR_ID)).thenReturn(levelFourSector);
    }

}