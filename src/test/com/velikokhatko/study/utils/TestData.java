package com.velikokhatko.study.utils;

import com.velikokhatko.study.model.Track;
import com.velikokhatko.study.model.UserProfile;
import com.velikokhatko.study.model.Workout;
import com.velikokhatko.study.model.enums.Intensive;
import com.velikokhatko.study.model.enums.WorkoutType;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestData {

    private static long idGen = 1000;
    private static final String PICTURE_PATH = "image/spring.jpeg";

    public static final UserProfile USER_PROFILE_1 = new UserProfile();
    public static final Workout WORKOUT_1 = new Workout();
    public static final Track TRACK_1 = new Track();

    static {
        fillSimpleUserProfile1(USER_PROFILE_1);
        fillSimpleWorkout1(WORKOUT_1);
        fillTrack(TRACK_1);
    }

    private static void fillSimpleWorkout1(Workout workout) {
        workout.setId(++idGen);
        workout.setIntensive(Intensive.NORMAL);
        workout.setDuration(LocalTime.of(1, 2));
        workout.setType(WorkoutType.RUN);
        workout.setDate(LocalDateTime.now());
        workout.setTrack(TRACK_1);
    }

    private static void fillSimpleUserProfile1(UserProfile userProfile) {
        userProfile.setId(++idGen);
        userProfile.setName("testUser1");
        userProfile.setHeight(175D);
        userProfile.setWeight(82D);
        userProfile.setLunxVolume(3.2D);
    }

    private static void fillTrack(Track track) {
        track.setId(++idGen);
        track.setName("tesTrack1");
        try {
            track.setImage(TestUtils.getBytes(PICTURE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
