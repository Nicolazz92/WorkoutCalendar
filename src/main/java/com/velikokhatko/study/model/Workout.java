package com.velikokhatko.study.model;

import com.velikokhatko.study.model.base.BaseEntityNamed;
import com.velikokhatko.study.model.enums.Intensive;
import com.velikokhatko.study.model.enums.WorkoutType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = "userProfile")
@ToString(callSuper = true, exclude = "userProfile")
@Entity
public class Workout extends BaseEntityNamed {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id", insertable = false, updatable = false)
    private UserProfile userProfile;

    private LocalTime duration;

    private LocalDateTime date;

    @Enumerated(value = EnumType.STRING)
    private Intensive intensive;

    @Enumerated(value = EnumType.STRING)
    private WorkoutType type;

    @OneToOne
    @JoinColumn(name = "track_id")
    private Track track;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "parent_workout")
    private Set<Workout> workouts = new HashSet<>();

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        workouts.forEach(workout -> workout.setUserProfile(userProfile));
    }

    public Set<Workout> getWorkouts() {
        return Collections.unmodifiableSet(workouts);
    }

    public void setWorkouts(Set<Workout> workouts) {
        throw new UnsupportedOperationException("for modify 'workouts' collection " +
                "use addWorkout(Workout workout) and removeWorkout(Workout workout)");
    }

    public void addWorkout(Workout workout) {
        Objects.requireNonNull(workout, "workout cannot be null");
        if (usersDoesNotMatch(workout)) {
            throw new IllegalArgumentException("child workout's user does not match with current user");
        }
        workouts.add(workout);
    }

    public void removeWorkout(Workout workout) {
        Objects.requireNonNull(workout, "workout cannot be null");
        if (usersDoesNotMatch(workout)) {
            throw new IllegalArgumentException("child workout's user does not match with current user");
        }
        workouts.remove(workout);
    }

    private boolean usersDoesNotMatch(Workout workout) {
        return (Objects.isNull(userProfile) ^ Objects.isNull(workout.getUserProfile()))
                || (Objects.nonNull(userProfile) && Objects.nonNull(workout.getUserProfile()) && !userProfile.equals(workout.userProfile));
    }
}
