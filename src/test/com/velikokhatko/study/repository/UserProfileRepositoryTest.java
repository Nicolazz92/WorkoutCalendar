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
        UserProfile persistedUserProfile = userProfileRepository.save(userProfile);

        Workout workout = new Workout();
        workout.setName("testWorkout");
        Workout persistedWorkout = workoutRepository.save(workout);

        persistedUserProfile.addWorkout(persistedWorkout);
        assertEquals(workout, userProfileRepository.findById(persistedUserProfile.getId()).orElse(new UserProfile())
                .getWorkouts().stream().findFirst().orElse(new Workout()));
        assertEquals(userProfile, workoutRepository.findById(persistedWorkout.getId()).orElse(new Workout()).getUserProfile());
        persistedUserProfile.removeWorkout(persistedWorkout);
        assertNull(Objects.requireNonNull(workoutRepository.findById(persistedWorkout.getId()).orElse(null)).getUserProfile());
        assertEquals(0, Objects.requireNonNull(userProfileRepository.findById(persistedUserProfile.getId()).orElse(null))
                .getWorkouts().size());

        Contest contest = new Contest();
        contest.setName("testContest");
        Contest persistedContext = contestRepository.save(contest);

        persistedUserProfile.addContest(persistedContext);
        assertEquals(contest, userProfileRepository.findById(persistedUserProfile.getId()).orElse(new UserProfile())
                .getContests().stream().findFirst().orElse(new Contest()));
        assertEquals(userProfile, contestRepository.findById(persistedContext.getId()).orElse(new Contest()).getMembers()
                .stream().findFirst().orElse(new UserProfile()));
        persistedUserProfile.removeContest(persistedContext);
        assertEquals(0, Objects.requireNonNull(userProfileRepository.findById(persistedUserProfile.getId()).orElse(null))
                .getContests().size());
        assertEquals(0, Objects.requireNonNull(contestRepository.findById(persistedContext.getId()).orElse(null))
                .getMembers().size());

        Performance performance = new Performance();
        performance.setRun100m(LocalTime.of(0, 0, 16, 98));
        performance.setBike10km(LocalTime.of(0, 35, 20));
        performance.setSwim100m(LocalTime.of(0, 3, 11));
        Performance persistedPerformance = performanceRepository.save(performance);

        persistedUserProfile.setPerformance(persistedPerformance);
        performanceRepository.save(persistedPerformance);

        assertEquals(performance, userProfileRepository.findById(persistedUserProfile.getId()).orElse(new UserProfile()).getPerformance());
        assertEquals(userProfile, performanceRepository.findById(persistedPerformance.getId()).orElse(new Performance()).getUserProfile());
    }
}