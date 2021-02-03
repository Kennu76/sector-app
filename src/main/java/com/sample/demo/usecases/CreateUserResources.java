package com.sample.demo.usecases;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sample.demo.domain.SectorFlattened;
import com.sample.demo.domain.UserResource;
import com.sample.demo.model.User;
import com.sample.demo.model.UserSector;
import com.sample.demo.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserResources {
   
    @Autowired private GetSectorName getSectorName;
    @Autowired private UserRepository userRepository;

    public List<UserResource> execute() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(this::convertToUserResource)
                .collect(Collectors.toList());
    }

    public UserResource convertToUserResource(User user) {
        return UserResource.builder()
                .name(user.getName())
                .sectors(user.getUserSectors().stream()
                .map(this::composeFlattenedSector)
                .collect(Collectors.toList()))
                .agreeToTerms(user.isAgreeToTerms())
                .build();
    }

    private SectorFlattened composeFlattenedSector(UserSector userSector) {
        return SectorFlattened.builder().name(getSectorName.execute(userSector)).childrenId(userSector.getSectorId())
                .level(userSector.getSectorLevel()).expandable(userSector.isExpandable()).build();
    }

}
