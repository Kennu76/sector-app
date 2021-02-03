package com.sample.demo.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.sample.demo.model.LevelFourSector;
import com.sample.demo.model.LevelThreeSector;
import com.sample.demo.model.LevelTwoSector;
import com.sample.demo.model.MainSector;
import com.sample.demo.model.UserSector;
import com.sample.demo.repo.LevelFourSectorRepository;
import com.sample.demo.repo.LevelThreeSectorRepository;
import com.sample.demo.repo.LevelTwoSectorRepository;
import com.sample.demo.repo.MainSectorRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetMainSectorsTest {

    @InjectMocks
    GetMainSectors getMainSectors;

    @Mock
    MainSectorRepository mainSectorRepository;

    @Test
    public void whenLevelZero_thenReturnsMainSectorName() {
        mockMainSectorRepo();

        assertEquals("mainSector", getMainSectors.execute().get(0).getName());

    }


    private void mockMainSectorRepo() {
        MainSector mainSector = new MainSector();
        mainSector.setName("mainSector");
        when(mainSectorRepository.findAll()).thenReturn(List.of(mainSector));
    }


}