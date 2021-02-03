package com.sample.demo.test_util;

import java.util.List;

import com.sample.demo.domain.SectorFlattened;
import com.sample.demo.domain.UserResource;

public class GenerateClasses {
    
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

    
}
