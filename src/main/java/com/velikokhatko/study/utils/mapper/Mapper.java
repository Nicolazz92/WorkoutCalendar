package com.velikokhatko.study.utils.mapper;

import com.velikokhatko.study.model.base.BaseEntity;
import com.velikokhatko.study.view.dto.base.BaseEntityDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;

public interface Mapper<P extends BaseEntity, DTO extends BaseEntityDTO> {

    DTO persistedToDTO(P persisted);

    BaseEntityNamedDTO persistedToBaseEntityNamedDTO(P persisted);

    P dtoToTransient(DTO dto);
}
