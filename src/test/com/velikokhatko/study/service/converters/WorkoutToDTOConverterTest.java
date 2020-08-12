package com.velikokhatko.study.service.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.velikokhatko.study.view.dto.WorkoutDTO;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.velikokhatko.study.TestData.georgeRootBicycleWorkout;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkoutToDTOConverterTest {

    private WorkoutToDTOConverter mappingService;
    private ObjectMapper mapper;

    public WorkoutToDTOConverterTest() {
        mappingService = new WorkoutToDTOConverter();
        mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
    }

    @Test
    void entityToDTO() throws IOException {
        WorkoutDTO workoutDTOFromYaml = mapper.readValue(
                new ClassPathResource("yaml/testWorkoutDTO.yaml").getFile(), WorkoutDTO.class);
        WorkoutDTO workoutDTO = mappingService.convert(georgeRootBicycleWorkout);
        assertEquals(workoutDTOFromYaml, workoutDTO);
    }
}