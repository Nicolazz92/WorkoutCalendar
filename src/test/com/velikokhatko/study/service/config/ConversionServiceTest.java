package com.velikokhatko.study.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.velikokhatko.study.model.Track;
import com.velikokhatko.study.view.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.velikokhatko.study.TestData.bicycleTrack;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ConversionServiceTest {

    @Autowired
    @Qualifier("dtoConverter")
    private ConversionService conversionService;
    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
    }

    @Test
    public void conversionServiceTest() throws IOException {
        boolean canConvert = conversionService.canConvert(Track.class, TrackDTO.class);
        assertTrue(canConvert);

        TrackDTO trackDTOFromYaml = mapper.readValue(
                new ClassPathResource("yaml/testBicycleTrack.yaml").getFile(), TrackDTO.class);
        TrackDTO trackDTO = conversionService.convert(bicycleTrack, TrackDTO.class);
        assertEquals(trackDTOFromYaml, trackDTO);
    }
}