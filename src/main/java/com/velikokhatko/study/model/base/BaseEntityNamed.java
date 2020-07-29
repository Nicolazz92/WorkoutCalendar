package com.velikokhatko.study.model.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class BaseEntityNamed extends BaseEntity {

    @Column(name = "name")
    private String name;
}
