package com.sample.demo.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import com.sample.demo.domain.SectorFlattened;
import com.sample.demo.domain.UserResource;
import com.sample.demo.repo.UserRepository;
import com.sample.demo.test_util.GenerateClasses;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserResourcesTest {

    /**
     *
     */
    private static final String SECTOR_NAME = "sectorName";

    @InjectMocks
    CreateUserResources createUserResources;

    @Mock
    GetSectorName getSectorName;

    @Mock
    UserRepository userRepository;

    @Test
    public void whenRepoHasNoUser_returnsEmptyList() {
        mockUserRepoReturnsEmpty();
        assertEquals(List.of(), createUserResources.execute());
    }

    @Test
    public void whenRepoHasUser_returnUserConvertedToUserResource() {
        mockUserRepoReturnsUser();
        mockGetSectorNameReturnsName();

        UserResource actual = createUserResources.execute().get(0);
        SectorFlattened sectorFlattened = actual.getSectors().get(0);
        
        assertEquals(GenerateClasses.USER_NAME, actual.getName());
        assertTrue(actual.isAgreeToTerms());
        assertEquals(GenerateClasses.SECTOR_ID, sectorFlattened.getChildrenId());
        assertEquals(GenerateClasses.SECTOR_LEVEL, sectorFlattened.getLevel());
        assertEquals(SECTOR_NAME, sectorFlattened.getName());
    }

    private void mockUserRepoReturnsEmpty() {
        when(userRepository.findAll()).thenReturn(List.of());
    }

    private void mockUserRepoReturnsUser() {
        when(userRepository.findAll()).thenReturn(List.of(GenerateClasses.createUser()));
    }

    private void mockGetSectorNameReturnsName() {
        when(getSectorName.execute(any())).thenReturn(SECTOR_NAME);
    }

}