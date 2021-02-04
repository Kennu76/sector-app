package com.sample.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.demo.test_util.GenerateClasses;
import com.sample.demo.usecases.CreateUserResources;
import com.sample.demo.usecases.ProcessUserResource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @Mock
    private ProcessUserResource processUserResource;

    @Mock
    private CreateUserResources createUserResources;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(new UserController(processUserResource, createUserResources)).build();
    }

    @Test
    public void usersListTest() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk());

    }
    @Test
    public void saveUserTest() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/users/save").content(asJsonString(GenerateClasses.createUserRequest()))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
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
