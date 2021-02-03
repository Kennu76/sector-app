package com.sample.demo.usecases;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sample.demo.model.MainSector;
import com.sample.demo.repo.MainSectorRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetMainSectors {
    
    private MainSectorRepository mainSectorRepository;

    public List<MainSector> execute() {
        return StreamSupport.stream(mainSectorRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }
}