package com.velikokhatko.study.model;

import com.velikokhatko.study.model.base.BaseEntityNamed;
import com.velikokhatko.study.model.enums.Intensive;
import com.velikokhatko.study.model.enums.WorkoutType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true, exclude = "userProfile")
@ToString(callSuper = true, exclude = "userProfile")
@Entity
public class Workout extends BaseEntityNamed {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private UserProfile userProfile;

    private Integer duration;

    private LocalDateTime date;

    @Enumerated(value = EnumType.STRING)
    private Intensive intensive;

    @Enumerated(value = EnumType.STRING)
    private WorkoutType type;

    @OneToOne
    @JoinColumn(name = "id")
    private Track track;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<Workout> workouts = new ArrayList<>();
}
