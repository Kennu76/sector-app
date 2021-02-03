package com.sample.demo.test_util;

import java.util.List;
import java.util.Set;

import com.sample.demo.domain.SectorFlattened;
import com.sample.demo.domain.UserResource;
import com.sample.demo.model.User;
import com.sample.demo.model.UserSector;

public class GenerateClasses {
    
    public static final int SECTOR_LEVEL = 2;
    public static final int SECTOR_ID = 12;
    public static final String USER_NAME = "name";

    public static UserResource createUserRequest() {
        return UserResource.builder()
                .name(USER_NAME)
                .sectors(List.of(createSectorFlattened()))
                .agreeToTerms(true)
                .build();
    }

    private static SectorFlattened createSectorFlattened() {
        return SectorFlattened.builder().name("1").childrenId(1).level(2).build();
    }

    public static User createUser() {
        User user = new User();
        user.setName(USER_NAME);
        user.setAgreeToTerms(true);
        user.setUserSectors(Set.of(createUserSector()));
        return user;
    }

    private static UserSector createUserSector() {
        UserSector userSector = new UserSector();
        userSector.setSectorId(SECTOR_ID);
        userSector.setSectorLevel(SECTOR_LEVEL);
        return userSector;
    }

    
}
