package com.velikokhatko.study.model;

import com.velikokhatko.study.model.base.BaseEntityNamed;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"workouts", "contests"})
@ToString(callSuper = true, exclude = {"image", "workouts", "contests", "performance"})
@Entity
public class UserProfile extends BaseEntityNamed {

    @Lob
    @Column(columnDefinition = "BLOB")
    private Byte[] image;
    private Double weight;
    private Double height;
    private Double lunxVolume;

    @OneToMany(mappedBy = "userProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Workout> workouts = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @ManyToMany(mappedBy = "members")
    private Set<Contest> contests = new HashSet<>();

    public Set<Workout> getWorkouts() {
        return Collections.unmodifiableSet(workouts);
    }

    public void setWorkouts(Set<Workout> workouts) {
        throw new UnsupportedOperationException("for modify 'workouts' collection " +
                "use addWorkout(Workout workout) and removeWorkout(Workout workout)");
    }

    public void addWorkout(Workout workout) {
        Objects.requireNonNull(workout, "workout cannot be null");
        boolean added = workouts.add(workout);
        if (added) {
            workout.setUserProfile(this);
        }
    }

    public void removeWorkout(Workout workout) {
        Objects.requireNonNull(workout, "workout cannot be null");
        boolean removed = workouts.remove(workout);
        if (removed) {
            workout.setUserProfile(null);
        }
    }

    public void setPerformance(Performance performance) {
        Objects.requireNonNull(performance, "performance cannot be null");
        this.performance = performance;
        performance.setUserProfile(this);
    }

    public Set<Contest> getContests() {
        return Collections.unmodifiableSet(contests);
    }

    public void setContests(Set<Contest> contests) {
        throw new UnsupportedOperationException("for modify 'contests' collection " +
                "use addContest(Contest contest) and removeContest(Contest contest)");
    }

    public void addContest(Contest contest) {
        Objects.requireNonNull(contest, "contest cannot be null");
        boolean added = contests.add(contest);
        if (added) {
            contest.addMember(this);
        }
    }

    public void removeContest(Contest contest) {
        Objects.requireNonNull(contest, "contest cannot be null");
        boolean removed = contests.remove(contest);
        if (removed) {
            contest.removeMember(this);
        }
    }
}
