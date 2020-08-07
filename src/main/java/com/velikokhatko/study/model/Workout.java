package com.velikokhatko.study.model;

import com.velikokhatko.study.model.base.BaseEntityNamed;
import com.velikokhatko.study.model.enums.Intensive;
import com.velikokhatko.study.model.enums.WorkoutType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Экземпляр тренировки.
 * Добавление дочерней(child) тренировки к родительской(parent) происходит следующим образом.
 * child добавляется к тому же userProfile, что и parent, после чего child добавляется к parent.
 * Из userProfile удаляется child, и child остаётся связанным с userProfile только через parent.
 * Это сделено для невозможности добавить к тренировке parent, созданной одним пользователем,
 * тренировку child, созданную другим пользователем.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "userProfile")
@ToString(callSuper = true, exclude = "userProfile")
@Entity
public class Workout extends BaseEntityNamed {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
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
        if (Objects.isNull(userProfile)) {
            this.userProfile = null;
            workouts.forEach(workout -> workout.setUserProfile(null));
        } else if (userProfile.getWorkouts().contains(this)) {
            this.userProfile = userProfile;
            workouts.forEach(userProfile::removeWorkout);
        } else {
            throw new IllegalArgumentException("userProfile does not contains workout. " +
                    "Use UserProfile.addWorkout(Workout workout) to modify userProfile.workouts. ");
        }
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
            throw new IllegalArgumentException("child workout's user does not match with parent user");
        }
        userProfile.removeWorkout(workout);
        workouts.add(workout);
    }

    public void removeWorkout(Workout workout) {
        Objects.requireNonNull(workout, "workout cannot be null");
        if (Objects.nonNull(workout.userProfile)) {
            throw new IllegalArgumentException("child workout cannot be linked directly to user");
        }
        workouts.remove(workout);
    }

    private boolean usersDoesNotMatch(Workout workout) {
        return (Objects.isNull(userProfile) ^ Objects.isNull(workout.getUserProfile()))
                || (Objects.nonNull(userProfile) && Objects.nonNull(workout.getUserProfile()) && !userProfile.equals(workout.userProfile));
    }
}
