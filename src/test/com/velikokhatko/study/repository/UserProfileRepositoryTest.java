package com.velikokhatko.study.repository;

import com.velikokhatko.study.model.Contest;
import com.velikokhatko.study.model.Performance;
import com.velikokhatko.study.model.UserProfile;
import com.velikokhatko.study.model.Workout;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
class UserProfileRepositoryTest {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private ContestRepository contestRepository;
    @Autowired
    private PerformanceRepository performanceRepository;

    @Test
    public void saveTest() {
        UserProfile userProfile = new UserProfile();
        userProfile.setName("userProfile");
        userProfileRepository.save(userProfile);

        Workout workout = new Workout();
        workout.setName("testWorkout");
        workoutRepository.save(workout);

        userProfile.addWorkout(workout);
        assertEquals(workout, userProfileRepository.findById(userProfile.getId()).orElse(new UserProfile())
                .getWorkouts().stream().findFirst().orElse(new Workout()));
        assertEquals(userProfile, workoutRepository.findById(workout.getId()).orElse(new Workout()).getUserProfile());
        userProfile.removeWorkout(workout);
        assertNull(Objects.requireNonNull(workoutRepository.findById(workout.getId()).orElse(null)).getUserProfile());
        assertEquals(0, Objects.requireNonNull(userProfileRepository.findById(userProfile.getId()).orElse(null))
                .getWorkouts().size());

        Contest contest = new Contest();
        contest.setName("testContest");
        contestRepository.save(contest);

        userProfile.addContest(contest);
        assertEquals(contest, userProfileRepository.findById(userProfile.getId()).orElse(new UserProfile())
                .getContests().stream().findFirst().orElse(new Contest()));
        assertEquals(userProfile, contestRepository.findById(contest.getId()).orElse(new Contest()).getMembers()
                .stream().findFirst().orElse(new UserProfile()));
        userProfile.removeContest(contest);
        assertEquals(0, Objects.requireNonNull(userProfileRepository.findById(userProfile.getId()).orElse(null))
                .getContests().size());
        assertEquals(0, Objects.requireNonNull(contestRepository.findById(contest.getId()).orElse(null))
                .getMembers().size());

        Performance performance = new Performance();
        performance.setRun100m(LocalTime.of(0, 0, 16, 98));
        performance.setBike10km(LocalTime.of(0, 35, 20));
        performance.setSwim100m(LocalTime.of(0, 3, 11));
        performanceRepository.save(performance);

        userProfile.setPerformance(performance);
        performanceRepository.save(performance);

        assertEquals(performance, userProfileRepository.findById(userProfile.getId()).orElse(new UserProfile()).getPerformance());
        assertEquals(userProfile, performanceRepository.findById(performance.getId()).orElse(new Performance()).getUserProfile());
    }
}