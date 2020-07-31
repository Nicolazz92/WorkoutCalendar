package com.velikokhatko.study.model.base;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("Lombok")
@Data
@ToString(callSuper = true)
@MappedSuperclass
public class BaseEntityNamed extends BaseEntity {

    @Column(name = "name")
    private String name;
}
