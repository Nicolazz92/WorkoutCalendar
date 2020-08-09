package com.velikokhatko.study.view.dto;

import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ContestDTO extends BaseEntityNamedDTO {
    private BaseEntityNamedDTO track;
    private LocalDate date;
    private List<BaseEntityNamedDTO> members;
    private BaseEntityNamedDTO winner;

    @Builder
    public ContestDTO(Long id,
                      String name,
                      BaseEntityNamedDTO track,
                      LocalDate date,
                      List<BaseEntityNamedDTO> members,
                      BaseEntityNamedDTO winner) {
        super(id, name);
        this.track = track;
        this.date = date;
        this.members = members;
        this.winner = winner;
    }
}
