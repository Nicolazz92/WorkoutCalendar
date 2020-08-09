package com.velikokhatko.study.controller;

import com.velikokhatko.study.service.UserProfileService;
import com.velikokhatko.study.view.dto.UserProfileDTO;
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

class UserProfileControllerTest {

    @Mock
    private UserProfileService userProfileService;
    private MockMvc mockMvc;
    List<UserProfileDTO> userProfileDTOs = Arrays.asList(
            UserProfileDTO.builder().id(1000L).name("testName1").build(),
            UserProfileDTO.builder().id(1001L).name("testName2").build()
    );

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(userProfileService.getUserProfileDTOs()).thenReturn(userProfileDTOs);

        UserProfileController userProfileController = new UserProfileController(userProfileService);
        mockMvc = MockMvcBuilders.standaloneSetup(userProfileController)
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