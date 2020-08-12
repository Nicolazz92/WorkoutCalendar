package com.velikokhatko.study.service.converters.base;

import com.velikokhatko.study.model.base.BaseEntity;
import com.velikokhatko.study.view.dto.base.BaseEntityDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BaseEntityConverter implements Converter<BaseEntity, BaseEntityDTO> {

    @Override
    public BaseEntityDTO convert(BaseEntity entity) {
        return new BaseEntityDTO(entity.getId());
    }
}
