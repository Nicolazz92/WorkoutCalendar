package com.velikokhatko.study.service.mapper;

import com.velikokhatko.study.model.Track;
import com.velikokhatko.study.view.dto.TrackDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrackMapperTest {

    private static TrackMapper trackMapper;
    private static Track origTrack;
    private static TrackDTO origTrackDTO;

    static {
        trackMapper = new TrackMapper();

        origTrack = new Track();
        origTrack.setId(100L);
        origTrack.setName("testName");
        origTrack.setImage(new Byte[]{0b11, 0b01});

        origTrackDTO = new TrackDTO();
        origTrackDTO.setId(origTrack.getId());
        origTrackDTO.setName(origTrack.getName());
        origTrackDTO.setImage(origTrack.getImage());
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