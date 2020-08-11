package com.velikokhatko.study.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.velikokhatko.study.view.dto.ContestDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.velikokhatko.study.TestData.bicycleContest;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContestDTOMappingServiceTest {

    private ContestDTOMappingService mappingService;
    private ObjectMapper mapper;

    public ContestDTOMappingServiceTest() {
        mappingService = new ContestDTOMappingService();
        mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
    }

    @Test
    void entityToDTO() throws IOException {
        ContestDTO contestDTO = mappingService.entityToDTO(bicycleContest);
        ContestDTO contestDTOFromYaml = mapper.readValue(
                new ClassPathResource("yaml/testBicycleContest.yaml").getFile(), ContestDTO.class);
        assertEquals(contestDTOFromYaml, contestDTO);
    }

    @Test
    @Disabled
    void dtoToEntity() throws IOException {
    }
}