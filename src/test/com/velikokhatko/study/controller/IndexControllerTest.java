package com.velikokhatko.study.controller;

import com.velikokhatko.study.service.ContestService;
import com.velikokhatko.study.service.TrackService;
import com.velikokhatko.study.service.UserProfileService;
import com.velikokhatko.study.view.dto.UserProfileDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

class IndexControllerTest {

    @Mock
    private UserProfileService userProfileService;
    @Mock
    private ContestService contestService;
    @Mock
    private TrackService trackService;
    private MockMvc mockMvc;
    private List<BaseEntityNamedDTO> userProfileDTOs = Arrays.asList(
            UserProfileDTO.builder().id(1000L).name("testName1").build(),
            UserProfileDTO.builder().id(1001L).name("testName2").build()
    );

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(userProfileService.getUserProfileDTOs()).thenReturn(userProfileDTOs);

        IndexController indexController = new IndexController(userProfileService, contestService, trackService);

        mockMvc = MockMvcBuilders.standaloneSetup(indexController)
                .setControllerAdvice()
                .build();
    }

    @Test
    public void getReducedUsersTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"))
                .andExpect(MockMvcResultMatchers.model().attribute("users", userProfileDTOs));

        Mockito.verify(userProfileService, Mockito.times(1)).getUserProfileDTOs();
    }
}