package com.velikokhatko.study.service.converters;

import com.velikokhatko.study.model.Contest;
import com.velikokhatko.study.view.dto.ContestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class ContestToDTOConverter implements Converter<Contest, ContestDTO> {

    @Override
    public ContestDTO convert(Contest entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, ContestDTO.class);
    }
}
