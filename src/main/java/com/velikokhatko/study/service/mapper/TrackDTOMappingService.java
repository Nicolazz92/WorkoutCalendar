package com.velikokhatko.study.service.mapper;

import com.velikokhatko.study.model.Track;
import com.velikokhatko.study.service.mapper.base.BaseNamedMappingService;
import com.velikokhatko.study.view.dto.TrackDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TrackDTOMappingService extends BaseNamedMappingService<Track, TrackDTO> {

    @Override
    public TrackDTO entityToDTO(Track entity) {
        return TrackDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .build();
    }

    @Override
    public Track dtoToEntity(TrackDTO dto) {
        throw new UnsupportedOperationException("Not Implemented Yet");
    }
}
