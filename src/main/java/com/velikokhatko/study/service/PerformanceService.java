package com.velikokhatko.study.service;

import com.velikokhatko.study.model.Performance;
import com.velikokhatko.study.repository.PerformanceRepository;
import com.velikokhatko.study.view.dto.PerformanceDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final ConversionService conversionService;

    public PerformanceService(PerformanceRepository performanceRepository,
                              @Qualifier("dtoConverter") ConversionService conversionService) {
        this.performanceRepository = performanceRepository;
        this.conversionService = conversionService;
    }

    @Transactional(readOnly = true)
    public Performance getPerformanceById(Long performanceId) {
        return performanceRepository.findById(performanceId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public PerformanceDTO getPerformanceDTOById(Long workoutId) {
        return conversionService.convert(getPerformanceById(workoutId), PerformanceDTO.class);
    }
}
