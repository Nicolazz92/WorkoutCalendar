package com.velikokhatko.study.service.mapper;

import com.velikokhatko.study.model.Track;
import com.velikokhatko.study.view.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrackDTOMappingServiceTest {

    private TrackDTOMappingService trackMapper;
    private Track origTrack;
    private TrackDTO origTrackDTO;

    @BeforeEach
    public void setUp() {
        trackMapper = new TrackDTOMappingService();

        origTrack = new Track();
        origTrack.setId(100L);
        origTrack.setName("testName");
        origTrack.setImage(new Byte[]{0b11, 0b01});

        origTrackDTO = TrackDTO.builder()
                .id(origTrack.getId())
                .name(origTrack.getName())
                .image(origTrack.getImage())
                .build();
    }

    @Test
    public void entityToDTOTest() {
        TrackDTO trackDTO = trackMapper.entityToDTO(origTrack);
        assertEquals(origTrackDTO, trackDTO);
    }

    @Test
    public void dtoToEntityTest() {
        Track track = trackMapper.dtoToEntity(origTrackDTO);
        assertEquals(origTrack, track);
    }
}