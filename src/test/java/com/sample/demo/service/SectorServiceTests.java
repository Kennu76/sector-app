package com.sample.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.sample.demo.repo.MainSectorRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SectorServiceTests {
    @InjectMocks
    SectorService sectorService;

    @Mock 
    MainSectorRepository mainSectorRepository;

    void setup(){
        sectorService = new SectorService(mainSectorRepository);
    }

    @Test
    public void whenRepoEmpty_thenFindAll_returnsEmptyList() {
        repoReturnsEmpty();
        assertEquals(List.of(), sectorService.findAllMainSectors());
    }

    void repoReturnsEmpty(){
        when(mainSectorRepository.findAll()).thenReturn(List.of());
    }
}