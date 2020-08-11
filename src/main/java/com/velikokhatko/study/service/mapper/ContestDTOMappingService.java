package com.velikokhatko.study.service.mapper;

import com.velikokhatko.study.model.Contest;
import com.velikokhatko.study.service.mapper.base.BaseNamedMappingService;
import com.velikokhatko.study.view.dto.ContestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ContestDTOMappingService extends BaseNamedMappingService<Contest, ContestDTO> {

    @Override
    public ContestDTO entityToDTO(Contest entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, ContestDTO.class);
    }

    @Override
    public Contest dtoToEntity(ContestDTO dto) {
        throw new UnsupportedOperationException("Not Implemented Yet");
    }
}
