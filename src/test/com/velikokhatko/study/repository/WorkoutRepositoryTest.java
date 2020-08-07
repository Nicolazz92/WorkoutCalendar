package com.velikokhatko.study.repository;

import com.velikokhatko.study.model.Track;
import com.velikokhatko.study.model.UserProfile;
import com.velikokhatko.study.model.Workout;
import com.velikokhatko.study.model.enums.Intensive;
import com.velikokhatko.study.model.enums.WorkoutType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class WorkoutRepositoryTest {

    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private TrackRepository trackRepository;

    @Test
    public void saveTest() {
        UserProfile userProfile = new UserProfile();
        userProfile.setName("testUser");
        userProfileRepository.save(userProfile);

        Track track = new Track();
        track.setName("testTrack");
        trackRepository.save(track);

        Workout workoutParent = new Workout();
        workoutParent.setDuration(LocalTime.of(1, 12));
        workoutParent.setDate(LocalDateTime.now());
        workoutParent.setIntensive(Intensive.NORMAL);
        workoutParent.setType(WorkoutType.BICYCLE);
        workoutParent.setTrack(track);
        workoutRepository.save(workoutParent);

        Workout workoutChild = new Workout();
        workoutChild.setDuration(LocalTime.of(0, 30));
        workoutChild.setDate(LocalDateTime.now());
        workoutChild.setIntensive(Intensive.LOW);
        workoutChild.setType(WorkoutType.RUN);
        workoutChild.setTrack(track);
        workoutRepository.save(workoutParent);

        userProfile.addWorkout(workoutChild);
        userProfile.addWorkout(workoutParent);
        workoutParent.addWorkout(workoutChild);

        assertEquals(workoutChild, userProfileRepository.findById(userProfile.getId()).orElse(new UserProfile())
                .getWorkouts().stream().filter(workout -> !workout.getWorkouts().isEmpty()).findFirst().orElse(new Workout())
                .getWorkouts().stream().findFirst().orElse(new Workout()));
    }
}