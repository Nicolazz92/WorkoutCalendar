package com.velikokhatko.study.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.velikokhatko.study.view.dto.PerformanceDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.velikokhatko.study.TestData.georgePerformance;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PerformanceDTOMappingServiceTest {

    private PerformanceDTOMappingService mappingService;
    private ObjectMapper mapper;

    public PerformanceDTOMappingServiceTest() {
        mappingService = new PerformanceDTOMappingService();
        mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();

//                PerformanceDTO performanceDTO = mappingService.entityToDTO(georgePerformance);
//        mapper.writeValue(new File("src/test/resources/yaml/testPerformanceDTO.yaml"), performanceDTO);
    }

    @Test
    void entityToDTO() throws IOException {
        PerformanceDTO performanceDTO = mappingService.entityToDTO(georgePerformance);
        PerformanceDTO contestDTOFromYaml = mapper.readValue(
                new ClassPathResource("yaml/testPerformanceDTO.yaml").getFile(), PerformanceDTO.class);
        assertEquals(contestDTOFromYaml, performanceDTO);
    }

    @Test
    @Disabled
    void dtoToEntity() {
    }
}