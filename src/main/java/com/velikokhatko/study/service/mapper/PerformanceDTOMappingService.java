package com.velikokhatko.study.service.mapper;

import com.velikokhatko.study.model.Performance;
import com.velikokhatko.study.service.mapper.base.BaseMappingService;
import com.velikokhatko.study.view.dto.PerformanceDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PerformanceDTOMappingService extends BaseMappingService<Performance, PerformanceDTO> {

    @Override
    public PerformanceDTO entityToDTO(Performance entity) {
        return PerformanceDTO.builder()
                .id(entity.getId())
                .userProfile(null)
                .run100m(entity.getRun100m())
                .run500m(entity.getRun500m())
                .run1000m(entity.getRun1000m())
                .swim100m(entity.getSwim100m())
                .swim500m(entity.getSwim500m())
                .swim1000m(entity.getSwim1000m())
                .bike10km(entity.getBike10km())
                .bike25km(entity.getBike25km())
                .build();
    }

    @Override
    public Performance dtoToEntity(PerformanceDTO dto) {
        throw new UnsupportedOperationException("Not Implemented Yet");
    }
}
