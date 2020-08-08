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
    private TrackDTO track;
    private LocalDate date;
    private List<UserProfileDTO> members;
    private UserProfileDTO winner;

    @Builder
    public ContestDTO(Long id,
                      String name,
                      TrackDTO track,
                      LocalDate date,
                      List<UserProfileDTO> members,
                      UserProfileDTO winner) {
        super(id, name);
        this.track = track;
        this.date = date;
        this.members = members;
        this.winner = winner;
    }
}
