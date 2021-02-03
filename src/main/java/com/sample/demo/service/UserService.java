package com.sample.demo.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sample.demo.domain.SectorFlattened;
import com.sample.demo.domain.UserRequest;
import com.sample.demo.model.User;
import com.sample.demo.model.UserSector;
import com.sample.demo.repo.UserRepository;
import com.sample.demo.repo.UserSectorRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private UserSectorRepository userSectorRepository;

    public List<User> findAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void processUserRequest(UserRequest userRequest) {
        User user = composeUser(userRequest);
        saveUser(user);
        composeAndSaveUserSectors(userRequest, user);
    }

    private void composeAndSaveUserSectors(UserRequest userRequest, User user) {
        userRequest.getSectors().stream()
            .map(sector -> composeUserSector(sector, user))
            .forEach(this::saveUserSector);
    }

    private User composeUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .agreeToTerms(userRequest.isAgreeToTerms())
                .build();
    }

    public UserSector composeUserSector(SectorFlattened sectorFlattened, User user) {
        return UserSector.builder()
                .sectorId(sectorFlattened.getChildrenId())
                .sectorLevel(sectorFlattened.getLevel())
                .user(user)
                .expandable(sectorFlattened.isExpandable())
                .build();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveUserSector(UserSector userSector) {
        userSectorRepository.save(userSector);
    }

}
