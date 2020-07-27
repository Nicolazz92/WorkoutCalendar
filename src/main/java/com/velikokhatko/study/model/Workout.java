package com.velikokhatko.study.model;

import com.velikokhatko.study.model.base.BaseEntityNamed;
import com.velikokhatko.study.model.enums.Intensive;
import com.velikokhatko.study.model.enums.WorkoutType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Workout extends BaseEntityNamed {
    private UserProfile user;
    private Integer duration;
    private LocalDateTime date;
    private Intensive intensive;
    private WorkoutType type;
    private Track track;
    private List<Workout> workouts;
}
