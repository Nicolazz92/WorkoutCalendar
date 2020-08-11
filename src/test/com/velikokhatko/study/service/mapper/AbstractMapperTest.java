package com.velikokhatko.study.service.mapper;

import com.velikokhatko.study.model.*;
import com.velikokhatko.study.model.enums.Intensive;
import com.velikokhatko.study.model.enums.WorkoutType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AbstractMapperTest {

    static protected UserProfile george = getGeorge();
    static protected Performance georgePerformance = getGeorgePerformance();
    static protected UserProfile michael = getMichael();
    static protected Performance michaelPerformance = getMichaelPerformance();
    static protected Track runningTrack = getRunningTrack();
    static protected Track bicycleTrack = getBicycleTrack();
    static protected Track swimmingTrack = getSwimmingTrack();
    static protected Contest runningContest = getRunningContest();
    static protected Contest bicycleContest = getBicycleContest();
    static protected Workout georgeRootBicycleWorkout = getGeorgeRootBicycleWorkout();
    static protected Workout georgeRunningWorkout = getGeorgeRunningWorkout();
    static protected Workout georgeLeafSwimmingWorkout = getGeorgeLeafSwimmingWorkout();
    static protected Workout michaelRootBicycleWorkout = getMichaelRootBicycleWorkout();
    static protected Workout michaelLeafRunningWorkout = getMichaelLeafRunningWorkout();

    private static long counter = 0;

    private static long getId() {
        return ++counter;
    }

    static {
        george.setPerformance(georgePerformance);
        michael.setPerformance(michaelPerformance);

        runningContest.setTrack(runningTrack);
        runningContest.addMember(george);
        runningContest.addMember(michael);
        runningContest.setWinner(george);

        bicycleContest.setTrack(bicycleTrack);
        bicycleContest.addMember(george);
        bicycleContest.addMember(michael);
        bicycleContest.setWinner(michael);

        georgeRootBicycleWorkout.setTrack(bicycleTrack);
        george.addWorkout(georgeRootBicycleWorkout);

        georgeRunningWorkout.setTrack(runningTrack);
        george.addWorkout(georgeRunningWorkout);
        georgeRootBicycleWorkout.addWorkout(georgeRunningWorkout);

        georgeLeafSwimmingWorkout.setTrack(swimmingTrack);
        george.addWorkout(georgeLeafSwimmingWorkout);
        georgeRunningWorkout.addWorkout(georgeLeafSwimmingWorkout);

        michaelRootBicycleWorkout.setTrack(bicycleTrack);
        michael.addWorkout(michaelRootBicycleWorkout);

        michaelLeafRunningWorkout.setTrack(runningTrack);
        michael.addWorkout(michaelLeafRunningWorkout);
        michaelRootBicycleWorkout.addWorkout(michaelLeafRunningWorkout);
    }

    private static UserProfile getGeorge() {
        UserProfile george = new UserProfile();
        george.setId(getId());
        george.setName("George");
        george.setImage(new Byte[]{0b01, 0b11});
        george.setWeight(82.1D);
        george.setHeight(173.2D);
        george.setLunxVolume(3.7D);
        return george;
    }

    private static UserProfile getMichael() {
        UserProfile michael = new UserProfile();
        michael.setId(getId());
        michael.setName("Michael");
        michael.setImage(new Byte[]{0b11, 0b01});
        michael.setWeight(85.2D);
        michael.setHeight(170.1D);
        michael.setLunxVolume(3.2D);
        return michael;
    }

    private static Performance getGeorgePerformance() {
        Performance georgePerformance = new Performance();
        georgePerformance.setId(getId());
        georgePerformance.setRun100m(LocalTime.of(0, 0, 27, 700));
        georgePerformance.setRun500m(LocalTime.of(0, 1, 34, 300));
        georgePerformance.setRun1000m(LocalTime.of(0, 3, 10));
        georgePerformance.setSwim100m(LocalTime.of(0, 2, 20));
        georgePerformance.setSwim500m(LocalTime.of(0, 11, 40));
        georgePerformance.setSwim1000m(null);
        georgePerformance.setBike10km(LocalTime.of(0, 32, 53));
        georgePerformance.setBike25km(LocalTime.of(1, 14, 32));
        return georgePerformance;
    }

    private static Performance getMichaelPerformance() {
        Performance michaelPerformance = new Performance();
        michaelPerformance.setId(getId());
        michaelPerformance.setRun100m(LocalTime.of(0, 0, 28, 730));
        michaelPerformance.setRun500m(LocalTime.of(0, 1, 36, 200));
        michaelPerformance.setRun1000m(LocalTime.of(0, 3, 40));
        michaelPerformance.setSwim100m(LocalTime.of(0, 2, 45));
        michaelPerformance.setSwim500m(LocalTime.of(0, 12, 50));
        michaelPerformance.setSwim1000m(LocalTime.of(1, 20, 32));
        michaelPerformance.setBike10km(LocalTime.of(0, 35, 22));
        michaelPerformance.setBike25km(LocalTime.of(1, 21, 11));
        return michaelPerformance;
    }

    private static Track getRunningTrack() {
        Track runningTrack = new Track();
        runningTrack.setId(getId());
        runningTrack.setName("Running Track");
        runningTrack.setImage(new Byte[]{0b01, 0b10, 0b11});
        return runningTrack;
    }

    private static Track getBicycleTrack() {
        Track bicycleTrack = new Track();
        bicycleTrack.setId(getId());
        bicycleTrack.setName("Bicycle Track");
        bicycleTrack.setImage(new Byte[]{0b10, 0b01, 0b11});
        return bicycleTrack;
    }

    private static Track getSwimmingTrack() {
        Track swimmingTrack = new Track();
        swimmingTrack.setId(getId());
        swimmingTrack.setName("Swimming Track");
        swimmingTrack.setImage(new Byte[]{0b01, 0b11, 0b10});
        return swimmingTrack;
    }

    private static Contest getRunningContest() {
        Contest runContest = new Contest();
        runContest.setId(getId());
        runContest.setName("Super Running!");
        runContest.setDate(LocalDate.of(2020, 7, 12));
        return runContest;
    }

    private static Contest getBicycleContest() {
        Contest bikeContest = new Contest();
        bikeContest.setId(getId());
        bikeContest.setName("Super Bicycling!");
        bikeContest.setDate(LocalDate.of(2020, 7, 13));
        return bikeContest;
    }

    private static Workout getGeorgeRootBicycleWorkout() {
        Workout georgeRootBicycleWorkout = new Workout();
        georgeRootBicycleWorkout.setId(getId());
        georgeRootBicycleWorkout.setName("Low George Bicycle Workout");
        georgeRootBicycleWorkout.setDuration(LocalTime.of(1, 32));
        georgeRootBicycleWorkout.setDate(LocalDateTime.of(2020, 6, 10, 15, 0));
        georgeRootBicycleWorkout.setIntensive(Intensive.LOW);
        georgeRootBicycleWorkout.setType(WorkoutType.BICYCLE);
        return georgeRootBicycleWorkout;
    }

    private static Workout getGeorgeRunningWorkout() {
        Workout georgeRunningWorkout = new Workout();
        georgeRunningWorkout.setId(getId());
        georgeRunningWorkout.setName("Normal George Running Workout");
        georgeRunningWorkout.setDuration(LocalTime.of(0, 44));
        georgeRunningWorkout.setDate(LocalDateTime.of(2020, 6, 10, 17, 0));
        georgeRunningWorkout.setIntensive(Intensive.NORMAL);
        georgeRunningWorkout.setType(WorkoutType.RUN);
        return georgeRunningWorkout;
    }

    private static Workout getGeorgeLeafSwimmingWorkout() {
        Workout georgeLeafSwimmingWorkout = new Workout();
        georgeLeafSwimmingWorkout.setId(getId());
        georgeLeafSwimmingWorkout.setName("High George Swimming Workout");
        georgeLeafSwimmingWorkout.setDuration(LocalTime.of(0, 20));
        georgeLeafSwimmingWorkout.setDate(LocalDateTime.of(2020, 6, 10, 18, 0));
        georgeLeafSwimmingWorkout.setIntensive(Intensive.HIGH);
        georgeLeafSwimmingWorkout.setType(WorkoutType.SWIM);
        return georgeLeafSwimmingWorkout;
    }

    private static Workout getMichaelRootBicycleWorkout() {
        Workout michaelRootBicycleWorkout = new Workout();
        michaelRootBicycleWorkout.setId(getId());
        michaelRootBicycleWorkout.setName("Ultra Michael Bicycle Workout");
        michaelRootBicycleWorkout.setDuration(LocalTime.of(2, 11));
        michaelRootBicycleWorkout.setDate(LocalDateTime.of(2020, 6, 11, 14, 0));
        michaelRootBicycleWorkout.setIntensive(Intensive.ULTRA);
        michaelRootBicycleWorkout.setType(WorkoutType.BICYCLE);
        return michaelRootBicycleWorkout;
    }

    private static Workout getMichaelLeafRunningWorkout() {
        Workout michaelRootBicycleWorkout = new Workout();
        michaelRootBicycleWorkout.setId(getId());
        michaelRootBicycleWorkout.setName("Low Michael Running Workout");
        michaelRootBicycleWorkout.setDuration(LocalTime.of(0, 35));
        michaelRootBicycleWorkout.setDate(LocalDateTime.of(2020, 6, 11, 19, 11));
        michaelRootBicycleWorkout.setIntensive(Intensive.LOW);
        michaelRootBicycleWorkout.setType(WorkoutType.RUN);
        return michaelRootBicycleWorkout;
    }
}
