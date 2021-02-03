package com.sample.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import com.sample.demo.domain.SectorFlattened;
import com.sample.demo.domain.UserRequest;
import com.sample.demo.model.User;
import com.sample.demo.model.UserSector;
import com.sample.demo.repo.UserRepository;
import com.sample.demo.repo.UserSectorRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {
    /**
     *
     */
    private static final String USER_NAME = "name";

    private final SectorFlattened sectorFlattened1 = new SectorFlattened("1", 1, 1, true);

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserSectorRepository userSectorRepository;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Captor
    ArgumentCaptor<UserSector> userSectorCaptor;

    void setup() {
        userService = new UserService(userRepository, userSectorRepository);
    }

    @Test
    public void whenRepoEmpty_thenFindAll_returnsEmptyList() {
        userRepoReturnsEmpty();
        assertEquals(List.of(), userService.findAllUsers());
    }

    @Test
    public void whenSaveUserAndSector_thenUserAndSectorAreSaved() {
        UserRequest userRequest = createUserRequest();
        assertEquals(List.of(), userService.findAllUsers());

        userService.processUserRequest(userRequest);

        Mockito.verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        assertUser(capturedUser);

        Mockito.verify(userSectorRepository).save(userSectorCaptor.capture());
        UserSector capturedUserSector = userSectorCaptor.getValue();
        assertUserSector(capturedUser, capturedUserSector);

    }

    private void assertUserSector(User capturedUser, UserSector capturedUserSector) {
        assertEquals(capturedUser, capturedUserSector.getUser());
        assertEquals(1, capturedUserSector.getSectorId());
        assertEquals(1, capturedUserSector.getSectorLevel());
    }

    private void assertUser(User capturedUser) {
        assertEquals(USER_NAME, capturedUser.getName());
        assertTrue(capturedUser.isAgreeToTerms());
    }

    private UserRequest createUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName(USER_NAME);
        userRequest.setSectors(List.of(sectorFlattened1));
        userRequest.setAgreeToTerms(true);
        return userRequest;

    }

    void userRepoReturnsEmpty() {
        when(userRepository.findAll()).thenReturn(List.of());
    }

}