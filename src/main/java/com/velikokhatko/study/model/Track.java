package com.velikokhatko.study.model;

import com.velikokhatko.study.model.base.BaseEntityNamed;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = "image")
@Entity
public class Track extends BaseEntityNamed {

    @Lob
    @Column(columnDefinition = "BLOB")
    private Byte[] image;
}
