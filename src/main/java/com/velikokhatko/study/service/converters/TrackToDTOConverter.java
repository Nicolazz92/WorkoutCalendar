package com.velikokhatko.study.service.converters;

import com.velikokhatko.study.model.Track;
import com.velikokhatko.study.view.dto.TrackDTO;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class TrackToDTOConverter implements Converter<Track, TrackDTO> {

    @Override
    public TrackDTO convert(Track entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, TrackDTO.class);
    }
}
