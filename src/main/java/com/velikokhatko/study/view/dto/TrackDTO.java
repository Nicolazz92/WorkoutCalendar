package com.velikokhatko.study.view.dto;

import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TrackDTO extends BaseEntityNamedDTO {
    private Byte[] image;

    @Builder
    public TrackDTO(Long id, String name, Byte[] image) {
        super(id, name);
        this.image = image;
    }
}
