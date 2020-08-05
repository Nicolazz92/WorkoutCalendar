package com.velikokhatko.study.controller;

import com.velikokhatko.study.service.UserProfileService;
import com.velikokhatko.study.view.dto.UserReducedDTO;
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
    List<UserReducedDTO> userReducedDTOS = Arrays.asList(
            UserReducedDTO.builder().id(1000L).name("testName1").workouts(2).build(),
            UserReducedDTO.builder().id(1001L).name("testName2").workouts(3).build()
    );

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(userProfileService.getReducedUsers()).thenReturn(userReducedDTOS);

        UserProfileController userProfileController = new UserProfileController(userProfileService);
        mockMvc = MockMvcBuilders.standaloneSetup(userProfileController)
                .setControllerAdvice()
                .build();
    }

    @Test
    public void getReducedUsersTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("reducedUsers"))
                .andExpect(MockMvcResultMatchers.model().attribute("reducedUsers", userReducedDTOS));

        Mockito.verify(userProfileService, Mockito.times(1)).getReducedUsers();
    }
}