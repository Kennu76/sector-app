package com.sample.demo.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import com.sample.demo.domain.UserResource;
import com.sample.demo.model.User;
import com.sample.demo.model.UserSector;
import com.sample.demo.repo.UserRepository;
import com.sample.demo.repo.UserSectorRepository;
import com.sample.demo.test_util.GenerateClasses;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProcessUserRequestTest {

    @InjectMocks
    ProcessUserRequest processUserRequest;

    @Mock
    UserRepository userRepository;

    @Mock
    UserSectorRepository userSectorRepository;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Captor
    ArgumentCaptor<UserSector> userSectorCaptor;

    @Test
    public void whenSaveUserAndSector_thenUserAndSectorAreSaved() {
        UserResource userRequest = GenerateClasses.createUserRequest();

        processUserRequest.execute(userRequest);

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
        assertEquals(2, capturedUserSector.getSectorLevel());
    }

    private void assertUser(User capturedUser) {
        assertEquals(GenerateClasses.USER_NAME, capturedUser.getName());
        assertTrue(capturedUser.isAgreeToTerms());
    }


    void userRepoReturnsEmpty() {
        when(userRepository.findAll()).thenReturn(List.of());
    }

}