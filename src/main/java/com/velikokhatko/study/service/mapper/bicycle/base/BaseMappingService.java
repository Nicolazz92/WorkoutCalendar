package com.velikokhatko.study.service.mapper.bicycle.base;

import com.velikokhatko.study.model.base.BaseEntity;
import com.velikokhatko.study.model.base.BaseEntityNamed;
import com.velikokhatko.study.service.mapper.bicycle.Mapper;
import com.velikokhatko.study.view.dto.base.BaseEntityDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseMappingService<E extends BaseEntity, DTO extends BaseEntityDTO> implements Mapper<E, DTO> {

    @Override
    @Transactional(readOnly = true)
    public BaseEntityDTO entityToBaseEntityDTO(BaseEntity entity) {
        return new BaseEntityDTO(entity.getId());
    }

    @Override
    public BaseEntityNamedDTO entityToBaseEntityNamedDTO(BaseEntityNamed entity) {
        throw new UnsupportedOperationException("Performance entity has not name field");
    }
}
