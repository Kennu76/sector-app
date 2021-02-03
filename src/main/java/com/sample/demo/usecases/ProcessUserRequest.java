package com.sample.demo.usecases;

import com.sample.demo.domain.SectorFlattened;
import com.sample.demo.domain.UserResource;
import com.sample.demo.model.User;
import com.sample.demo.model.UserSector;
import com.sample.demo.repo.UserRepository;
import com.sample.demo.repo.UserSectorRepository;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProcessUserRequest {
    
    private UserRepository userRepository;
    private UserSectorRepository userSectorRepository;

    public void execute(@Validated UserResource userRequest) {
        User user = composeUser(userRequest);
        saveUser(user);
        composeAndSaveUserSectors(userRequest, user);
    }

    private void composeAndSaveUserSectors(UserResource userRequest, User user) {
        userRequest.getSectors().stream()
                .map(sector -> composeUserSector(sector, user))
                .forEach(this::saveUserSector);
    }

    private User composeUser(UserResource userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setAgreeToTerms(userRequest.isAgreeToTerms());
        return user;
    }

    public UserSector composeUserSector(SectorFlattened sectorFlattened, User user) {
        UserSector userSector = new UserSector();
        userSector.setSectorId(sectorFlattened.getChildrenId());
        userSector.setSectorLevel(sectorFlattened.getLevel());
        userSector.setUser(user);
        userSector.setExpandable(sectorFlattened.isExpandable());
        return userSector;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveUserSector(UserSector userSector) {
        userSectorRepository.save(userSector);
    }

}

