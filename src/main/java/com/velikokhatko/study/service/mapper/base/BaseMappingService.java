package com.velikokhatko.study.service.mapper.base;

import com.velikokhatko.study.model.base.BaseEntity;
import com.velikokhatko.study.model.base.BaseEntityNamed;
import com.velikokhatko.study.service.mapper.Mapper;
import com.velikokhatko.study.view.dto.base.BaseEntityDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;

public abstract class BaseMappingService<E extends BaseEntity, DTO extends BaseEntityDTO> implements Mapper<E, DTO> {

    @Override
    public BaseEntityDTO entityToBaseEntityDTO(BaseEntity entity) {
        return new BaseEntityDTO(entity.getId());
    }

    @Override
    public BaseEntityNamedDTO entityToBaseEntityNamedDTO(BaseEntityNamed entity) {
        throw new UnsupportedOperationException("Performance entity has not name field");
    }
}
