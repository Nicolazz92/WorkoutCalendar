package com.velikokhatko.study.service.converters.base;

import com.velikokhatko.study.model.base.BaseEntityNamed;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BaseEntityNamedConverter implements Converter<BaseEntityNamed, BaseEntityNamedDTO> {

    @Override
    public BaseEntityNamedDTO convert(BaseEntityNamed entity) {
        return new BaseEntityNamedDTO(entity.getId(), entity.getName());
    }
}
