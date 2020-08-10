package com.velikokhatko.study.service.mapper;

import com.velikokhatko.study.model.Track;
import com.velikokhatko.study.view.dto.TrackDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TrackMapper {

    public TrackDTO entityToDTO(Track track) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(track, TrackDTO.class);
    }

    public Track dtoToEntity(TrackDTO trackDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(trackDTO, Track.class);
    }
}
