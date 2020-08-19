package com.velikokhatko.study.model;

import com.velikokhatko.study.model.base.BaseEntityNamed;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = "image")
@Entity
@Builder
public class Track extends BaseEntityNamed {

    @Lob
    @Column(columnDefinition = "BLOB")
    private Byte[] image;
}
