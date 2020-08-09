package com.velikokhatko.study.view.dto;

import com.velikokhatko.study.model.enums.Intensive;
import com.velikokhatko.study.model.enums.WorkoutType;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WorkoutDTO extends BaseEntityNamedDTO {

    private BaseEntityNamedDTO userProfile;
    private boolean isRoot;
    private LocalTime duration;
    private LocalDateTime date;
    private Intensive intensive;
    private WorkoutType type;
    private BaseEntityNamedDTO track;
    private List<BaseEntityNamedDTO> workouts;

    @Builder
    public WorkoutDTO(Long id,
                      String name,
                      BaseEntityNamedDTO userProfile,
                      boolean isRoot,
                      LocalTime duration,
                      LocalDateTime date,
                      Intensive intensive,
                      WorkoutType type,
                      BaseEntityNamedDTO track,
                      List<BaseEntityNamedDTO> workouts) {
        super(id, name);
        this.userProfile = userProfile;
        this.isRoot = isRoot;
        this.duration = duration;
        this.date = date;
        this.intensive = intensive;
        this.type = type;
        this.track = track;
        this.workouts = workouts;
    }
}
