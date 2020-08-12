package com.velikokhatko.study.service.converters;

import com.velikokhatko.study.model.Workout;
import com.velikokhatko.study.view.dto.WorkoutDTO;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class WorkoutToDTOConverter implements Converter<Workout, WorkoutDTO> {

    @Override
    public WorkoutDTO convert(Workout entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, WorkoutDTO.class);
    }
}
