package com.velikokhatko.study.service.mapper;

import com.velikokhatko.study.model.Performance;
import com.velikokhatko.study.service.mapper.base.BaseMappingService;
import com.velikokhatko.study.view.dto.PerformanceDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PerformanceDTOMappingService extends BaseMappingService<Performance, PerformanceDTO> {

    @Override
    public PerformanceDTO entityToDTO(Performance entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, PerformanceDTO.class);
    }

    @Override
    public Performance dtoToEntity(PerformanceDTO dto) {
        throw new UnsupportedOperationException("Not Implemented Yet");
    }
}
