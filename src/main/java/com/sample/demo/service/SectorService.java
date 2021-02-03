package com.sample.demo.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sample.demo.model.MainSector;
import com.sample.demo.model.UserSector;
import com.sample.demo.repo.MainSectorRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class SectorService {
    private MainSectorRepository mainSectorRepository;

    public List<MainSector> findAllMainSectors() {
        return StreamSupport.stream(
            mainSectorRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

	public @NonNull String getSectorName(UserSector userSector) {
		return null;
	}


}