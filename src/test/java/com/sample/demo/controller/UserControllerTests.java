package com.sample.demo.controller;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.demo.domain.UserResource;
import com.sample.demo.service.UserService;
import com.sample.demo.test_util.GenerateClasses;
import com.sample.demo.usecases.CreateUserResources;
import com.sample.demo.usecases.ProcessUserRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import net.bytebuddy.asm.Advice.Argument;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private UserController UserController;
    
    @Mock
    private ProcessUserRequest processUserRequest;
    
    @Mock
    private CreateUserResources createUserResources;

    @Captor
    ArgumentCaptor<UserResource> userRequestCaptor;

    @Before
    public void setup(){
        mvc  = MockMvcBuilders.standaloneSetup(new UserController(processUserRequest, createUserResources)).build();
    }

    @Test
    public void saveUserTest() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                .post("/users/save")
                .content(asJsonString( GenerateClasses.createUserRequest()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

    }

    public static String asJsonString(final Object obj) {
        try {
            System.out.println(new ObjectMapper().writeValueAsString(obj));
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
