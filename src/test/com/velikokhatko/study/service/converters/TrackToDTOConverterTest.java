package com.velikokhatko.study.service.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.velikokhatko.study.view.dto.TrackDTO;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.velikokhatko.study.TestData.bicycleTrack;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TrackToDTOConverterTest {

    private TrackToDTOConverter mappingService;
    private ObjectMapper mapper;

    public TrackToDTOConverterTest() {
        mappingService = new TrackToDTOConverter();
        mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
    }

    @Test
    public void entityToDTO() throws IOException {
        TrackDTO trackDTOFromYaml = mapper.readValue(
                new ClassPathResource("yaml/testBicycleTrack.yaml").getFile(), TrackDTO.class);
        TrackDTO trackDTO = mappingService.convert(bicycleTrack);
        assertEquals(trackDTOFromYaml, trackDTO);
    }
}