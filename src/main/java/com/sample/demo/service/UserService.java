package com.sample.demo.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sample.demo.domain.SectorFlattened;
import com.sample.demo.domain.UserResource;
import com.sample.demo.model.User;
import com.sample.demo.model.UserSector;
import com.sample.demo.repo.UserRepository;
import com.sample.demo.repo.UserSectorRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private SectorService sectorService;
    private UserRepository userRepository;
    private UserSectorRepository userSectorRepository;

    public List<User> findAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    private SectorFlattened composeFlattenedSector(UserSector userSector) {
        return SectorFlattened.builder()
                .name(sectorService.getSectorName(userSector))
                .childrenId(userSector.getSectorId())
                .level(userSector.getSectorLevel())
                .expandable(userSector.isExpandable())
                .build();
    }



}
