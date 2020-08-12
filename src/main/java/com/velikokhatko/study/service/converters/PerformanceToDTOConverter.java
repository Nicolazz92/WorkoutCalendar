package com.velikokhatko.study.service.converters;

import com.velikokhatko.study.model.Performance;
import com.velikokhatko.study.view.dto.PerformanceDTO;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class PerformanceToDTOConverter implements Converter<Performance, PerformanceDTO> {

    @Override
    public PerformanceDTO convert(Performance entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, PerformanceDTO.class);
    }
}
