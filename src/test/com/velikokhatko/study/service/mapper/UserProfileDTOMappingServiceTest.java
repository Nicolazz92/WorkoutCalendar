package com.velikokhatko.study.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.velikokhatko.study.service.WorkoutService;
import com.velikokhatko.study.view.dto.UserProfileDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Collections;

import static com.velikokhatko.study.TestData.george;
import static com.velikokhatko.study.TestData.georgeRootBicycleWorkout;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserProfileDTOMappingServiceTest {

    private UserProfileDTOMappingService mappingService;
    private ObjectMapper mapper;
    @Mock
    private WorkoutService workoutService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(workoutService.getRootWorkoutsByUserId(george.getId()))
                .thenReturn(Collections.singletonList(georgeRootBicycleWorkout));

        mappingService = new UserProfileDTOMappingService(workoutService);
        mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
    }

    @Test
    void entityToDTO() throws IOException {
        UserProfileDTO userProfileDTOFromYaml = mapper.readValue(
                new ClassPathResource("yaml/testUserProfileDTO.yaml").getFile(), UserProfileDTO.class);
        UserProfileDTO userProfileDTO = mappingService.entityToDTO(george);
        assertEquals(userProfileDTOFromYaml, userProfileDTO);
    }

    @Test
    @Disabled
    void dtoToEntity() {
    }
}