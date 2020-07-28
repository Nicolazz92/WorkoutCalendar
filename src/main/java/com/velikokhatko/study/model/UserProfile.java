package com.velikokhatko.study.model;

import com.velikokhatko.study.model.base.BaseEntityNamed;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = "contests")
@ToString(callSuper = true, exclude = {"image", "workouts", "performance"})
@Entity
public class UserProfile extends BaseEntityNamed {

    @Lob
    private Byte[] image;
    private Double weight;
    private Double height;
    private Double lunxVolume;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private Set<Workout> workouts = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "id")
    private Performance performance;

    @ManyToMany(mappedBy = "members")
    private Set<Contest> contests = new HashSet<>();

    public void addWorkout(Workout workout) {
        workout.setUserProfile(this);
        workouts.add(workout);
    }

    public void removeWorkout(Workout workout) {
        boolean removed = workouts.remove(workout);
        if (removed) {
            workout.setUserProfile(null);
        }
    }

    public void setPerformance(Performance performance) {
        performance.setUserProfile(this);
        this.performance = performance;
    }

    //TODO добавить методы для ресолва bidirectional связи
}
