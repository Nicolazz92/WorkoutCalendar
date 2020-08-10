package com.velikokhatko.study.view.dto.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseEntityNamedDTO extends BaseEntityDTO {

    private String name;

    public BaseEntityNamedDTO(Long id, String name) {
        super(id);
        this.name = name;
    }
}
