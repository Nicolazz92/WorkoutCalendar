package com.velikokhatko.study.model.base;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
