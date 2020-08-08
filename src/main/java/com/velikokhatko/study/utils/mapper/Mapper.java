package com.velikokhatko.study.utils.mapper;

import com.velikokhatko.study.model.base.BaseEntity;
import com.velikokhatko.study.view.dto.base.BaseEntityDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;

public interface Mapper<E extends BaseEntity, DTO extends BaseEntityDTO> {

    DTO entityToDTO(E entity);

    BaseEntityNamedDTO entityToBaseEntityNamedDTO(E entity);

    E dtoToEntity(DTO dto);
}
