package com.velikokhatko.study.view.dto;

import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackDTO extends BaseEntityNamedDTO {
    private Byte[] image;

    @Builder
    public TrackDTO(Long id, String name, Byte[] image) {
        super(id, name);
        this.image = image;
    }
}
