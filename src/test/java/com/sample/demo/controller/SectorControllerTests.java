package com.sample.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sample.demo.usecases.GetMainSectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class SectorControllerTests {

    @Autowired
    private MockMvc mvc;

    @Mock
    private GetMainSectors getMainSectors;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(new SectorController(getMainSectors)).build();
    }

    @Test
    public void mainSectorsListTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/sectors")).andExpect(status().isOk());

    }
}
