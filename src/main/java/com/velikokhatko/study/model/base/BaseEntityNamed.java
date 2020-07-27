package com.velikokhatko.study.model.base;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class BaseEntityNamed extends BaseEntity {
    private String name;
}
