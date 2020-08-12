package com.velikokhatko.study.service.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.velikokhatko.study.view.dto.PerformanceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.velikokhatko.study.TestData.georgePerformance;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PerformanceDTOMappingServiceTest {

    private PerformanceToDTOConverter mappingService;
    private ObjectMapper mapper;

    public PerformanceDTOMappingServiceTest() {
        mappingService = new PerformanceToDTOConverter();
        mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
    }

    @Test
    void entityToDTO() throws IOException {
        PerformanceDTO performanceDTO = mappingService.convert(georgePerformance);
        PerformanceDTO contestDTOFromYaml = mapper.readValue(
                new ClassPathResource("yaml/testPerformanceDTO.yaml").getFile(), PerformanceDTO.class);
        assertEquals(contestDTOFromYaml, performanceDTO);
    }
}