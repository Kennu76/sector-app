package com.sample.demo.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.sample.demo.model.MainSector;
import com.sample.demo.repo.MainSectorRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetMainSectorsTest {

    /**
     *
     */
    private static final String MAIN_SECTOR = "mainSector";

    @InjectMocks
    GetMainSectors getMainSectors;

    @Mock
    MainSectorRepository mainSectorRepository;

    @Test
    public void whenLevelZero_thenReturnsMainSectorName() {
        mockMainSectorRepo();

        assertEquals(MAIN_SECTOR, getMainSectors.execute().get(0).getName());

    }

    private void mockMainSectorRepo() {
        MainSector mainSector = new MainSector();
        mainSector.setName(MAIN_SECTOR);
        when(mainSectorRepository.findAll()).thenReturn(List.of(mainSector));
    }

}