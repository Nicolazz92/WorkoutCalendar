package com.velikokhatko.study.model.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class BaseEntityNamed extends BaseEntity {
    private String name;
}
