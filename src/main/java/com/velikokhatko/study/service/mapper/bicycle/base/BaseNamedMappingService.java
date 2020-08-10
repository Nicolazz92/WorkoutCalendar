package com.velikokhatko.study.service.mapper.bicycle.base;

import com.velikokhatko.study.model.base.BaseEntityNamed;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseNamedMappingService<E extends BaseEntityNamed, DTO extends BaseEntityNamedDTO> extends BaseMappingService<E, DTO> {

    @Transactional(readOnly = true)
    public List<DTO> entitiesToDTOs(List<E> entities) {
        return entities.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BaseEntityNamedDTO> entitiesToBaseEntityNamedDTOs(List<BaseEntityNamed> entities) {
        return entities.stream()
                .map(this::entityToBaseEntityNamedDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BaseEntityNamedDTO entityToBaseEntityNamedDTO(BaseEntityNamed entity) {
        return new BaseEntityNamedDTO(entity.getId(), entity.getName());
    }
}
