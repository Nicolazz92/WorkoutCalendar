package com.velikokhatko.study.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.velikokhatko.study.view.dto.TrackDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.velikokhatko.study.TestData.bicycleTrack;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TrackDTOMappingServiceTest {

    private TrackDTOMappingService mappingService;
    private ObjectMapper mapper;

    public TrackDTOMappingServiceTest() {
        mappingService = new TrackDTOMappingService();
        mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
    }

    @Test
    public void entityToDTO() throws IOException {
        TrackDTO trackDTOFromYaml = mapper.readValue(
                new ClassPathResource("yaml/testBicycleTrack.yaml").getFile(), TrackDTO.class);
        TrackDTO trackDTO = mappingService.entityToDTO(bicycleTrack);
        assertEquals(trackDTOFromYaml, trackDTO);
    }

    @Test
    @Disabled
    public void dtoToEntity() {
    }
}