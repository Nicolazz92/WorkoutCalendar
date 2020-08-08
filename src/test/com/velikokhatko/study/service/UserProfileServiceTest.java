package com.velikokhatko.study.service;

import com.velikokhatko.study.repository.UserProfileRepository;
import com.velikokhatko.study.view.dto.UserProfileDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class UserProfileServiceTest {

    @Mock
    private UserProfileRepository userProfileRepository;
    private UserProfileService userProfileService;
    private List<UserProfileDTO> userReducedDTOS = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
//        Mockito.when(userProfileRepository.findAll()).thenReturn(userReducedDTOS);

    }

    @Test
    void getReducedUsers() {
    }
}