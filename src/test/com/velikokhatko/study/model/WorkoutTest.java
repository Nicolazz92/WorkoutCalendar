package com.velikokhatko.study.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutTest {

    @Test
    public void successTest() {
        Workout workout1 = new Workout();
        workout1.setName("workout1");
        UserProfile userProfile1 = new UserProfile();
        userProfile1.setName("userProfile1");
        userProfile1.addWorkout(workout1);

        Workout workout2 = new Workout();
        workout2.setName("workout2");
        userProfile1.addWorkout(workout2);

        Workout workout3 = new Workout();
        workout3.setName("workout3");
        userProfile1.addWorkout(workout3);

        workout1.addWorkout(workout2);
        workout2.addWorkout(workout3);
        assertEquals(workout2, workout1.getWorkouts().stream().findFirst().orElse(new Workout()));

        assertTrue(workout1.getIsRoot());
        assertFalse(workout2.getIsRoot());
        assertFalse(workout3.getIsRoot());

        workout1.removeWorkout(workout2);
        assertEquals(0, workout1.getWorkouts().size());
        workout2.removeWorkout(workout3);
        assertEquals(0, workout2.getWorkouts().size());
    }

    @Test
    public void failedTest() {
        UserProfile userProfile1 = new UserProfile();
        userProfile1.setName("userProfile1");

        Workout workout1 = new Workout();
        workout1.setName("workout1");
        userProfile1.addWorkout(workout1);
        assertThrows(IllegalArgumentException.class, () -> new UserProfile().addWorkout(workout1));

        Workout workout2 = new Workout();
        workout2.setName("workout2");

        assertThrows(IllegalArgumentException.class, () -> workout1.addWorkout(workout2));
        assertThrows(IllegalArgumentException.class, () -> workout1.removeWorkout(workout2));

        userProfile1.addWorkout(workout2);

        workout2.setIsRoot(Boolean.FALSE);
        assertThrows(IllegalArgumentException.class, () -> workout1.addWorkout(workout2));
        workout2.setIsRoot(Boolean.TRUE);

        assertThrows(IllegalArgumentException.class, () -> workout1.removeWorkout(workout2));
        assertThrows(IllegalArgumentException.class, () -> workout1.setUserProfile(new UserProfile()));


    }

    @Test
    public void cascadeUserProfileSetTest() {
        UserProfile userProfile1 = new UserProfile();
        userProfile1.setName("userProfile1");

        Workout workout1 = new Workout();
        workout1.setName("workout1");
        userProfile1.addWorkout(workout1);

        Workout workout2 = new Workout();
        workout2.setName("workout2");
        userProfile1.addWorkout(workout2);

        Workout workout3 = new Workout();
        workout3.setName("workout3");
        userProfile1.addWorkout(workout3);

        workout1.addWorkout(workout2);
        workout2.addWorkout(workout3);
        assertTrue(workout1.getWorkouts().stream().findFirst().isPresent());
        assertTrue(workout1.getWorkouts().stream().findFirst().orElse(new Workout())
                .getWorkouts().stream().findFirst().isPresent());
        assertEquals(3, userProfile1.getWorkouts().size());
        assertTrue(userProfile1.getWorkouts().stream().findFirst().isPresent());
        assertEquals(workout1, userProfile1.getWorkouts().stream().findFirst().get());
        assertEquals(userProfile1, workout2.getUserProfile());
        assertEquals(userProfile1, workout3.getUserProfile());
    }

    @Test
    public void unsupportedOperationsTest() {
        assertThrows(UnsupportedOperationException.class, () -> new Workout().setWorkouts(new HashSet<>()));
        assertThrows(UnsupportedOperationException.class, () -> new Workout().getWorkouts().add(new Workout()));
    }
}