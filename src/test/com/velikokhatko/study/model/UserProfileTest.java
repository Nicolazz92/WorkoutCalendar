package com.velikokhatko.study.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {

    private UserProfile userProfile;
    private Workout workout;
    private Contest contest;

    @BeforeEach
    private void initTestData() {
        userProfile = new UserProfile();
        userProfile.setName("testUser");

        workout = new Workout();
        workout.setName("testWorkout");

        contest = new Contest();
        contest.setName("testContest");
    }

    @Test
    public void workoutsBidirectionalTest() {
        userProfile.addWorkout(workout);
        assertEquals(workout, userProfile.getWorkouts().stream().findFirst().orElse(new Workout()));
        assertEquals(userProfile, workout.getUserProfile());

        userProfile.removeWorkout(workout);
        assertEquals(0, userProfile.getWorkouts().size());
        assertNull(workout.getUserProfile());
    }

    @Test
    public void contestsBidirectionalTest() {
        userProfile.addContest(contest);
        assertEquals(contest, userProfile.getContests().stream().findFirst().orElse(new Contest()));
        assertEquals(userProfile, contest.getMembers().stream().findFirst().orElse(new UserProfile()));

        userProfile.removeContest(contest);
        assertEquals(0, userProfile.getContests().size());
        assertEquals(0, contest.getMembers().size());
    }

    @Test
    public void unsupportedOperationsTest() {
        assertThrows(UnsupportedOperationException.class, () -> new UserProfile().setContests(new HashSet<>()));
        assertThrows(UnsupportedOperationException.class, () -> new UserProfile().getContests().add(new Contest()));
        assertThrows(UnsupportedOperationException.class, () -> new UserProfile().setWorkouts(new HashSet<>()));
        assertThrows(UnsupportedOperationException.class, () -> new UserProfile().getWorkouts().add(new Workout()));
    }
}
