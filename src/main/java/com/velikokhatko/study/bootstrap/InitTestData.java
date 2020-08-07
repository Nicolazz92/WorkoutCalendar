package com.velikokhatko.study.bootstrap;

import com.velikokhatko.study.model.Contest;
import com.velikokhatko.study.model.Performance;
import com.velikokhatko.study.model.Track;
import com.velikokhatko.study.model.UserProfile;
import com.velikokhatko.study.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Profile("init-test-data")
public class InitTestData implements CommandLineRunner {

    private final UserProfileRepository userProfileRepository;
    private final PerformanceRepository performanceRepository;
    private final ContestRepository contestRepository;
    private final TrackRepository trackRepository;
    private final WorkoutRepository workoutRepository;

    public InitTestData(UserProfileRepository userProfileRepository,
                        PerformanceRepository performanceRepository,
                        ContestRepository contestRepository,
                        TrackRepository trackRepository,
                        WorkoutRepository workoutRepository) {
        this.userProfileRepository = userProfileRepository;
        this.performanceRepository = performanceRepository;
        this.contestRepository = contestRepository;
        this.trackRepository = trackRepository;
        this.workoutRepository = workoutRepository;
    }

    @Override
    public void run(String... args) {
        UserProfile george = getGeorge();
        Performance georgePerformance = getGeorgePerformance();
        george.setPerformance(georgePerformance);

        UserProfile michael = getMichael();
        Performance michaelPerformance = getMichaelPerformance();
        michael.setPerformance(michaelPerformance);

        Track runningTrack = getRunningTrack();
        Track bicycleTrack = getBicycleTrack();

        Contest runningContest = getRunningContest();
        runningContest.setTrack(runningTrack);
        runningContest.addMember(george);
        runningContest.addMember(michael);
        runningContest.setWinner(george);

        Contest bikeContest = getBicycleContest();
        bikeContest.setTrack(bicycleTrack);
        bikeContest.addMember(george);
        bikeContest.addMember(michael);
        bikeContest.setWinner(michael);
    }

    private UserProfile getMichael() {
        UserProfile michael = new UserProfile();
        michael.setName("Michael");
        michael.setImage(new Byte[]{0b11, 0b01});
        michael.setWeight(85.2D);
        michael.setHeight(170.1D);
        michael.setLunxVolume(3.2D);
        userProfileRepository.save(michael);
        return michael;
    }

    private UserProfile getGeorge() {
        UserProfile george = new UserProfile();
        george.setName("George");
        george.setImage(new Byte[]{0b01, 0b11});
        george.setWeight(82.1D);
        george.setHeight(173.2D);
        george.setLunxVolume(3.7D);
        userProfileRepository.save(george);
        return george;
    }

    private Performance getMichaelPerformance() {
        Performance michaelPerformance = new Performance();
        michaelPerformance.setRun100m(LocalTime.of(0, 0, 28, 730));
        michaelPerformance.setRun500m(LocalTime.of(0, 1, 36, 200));
        michaelPerformance.setRun1000m(LocalTime.of(0, 3, 40));
        michaelPerformance.setSwim100m(LocalTime.of(0, 2, 45));
        michaelPerformance.setSwim500m(LocalTime.of(0, 12, 50));
        michaelPerformance.setSwim1000m(LocalTime.of(1, 20, 32));
        michaelPerformance.setBike10km(LocalTime.of(0, 35, 22));
        michaelPerformance.setBike25km(LocalTime.of(1, 21, 11));
        performanceRepository.save(michaelPerformance);
        return michaelPerformance;
    }

    private Performance getGeorgePerformance() {
        Performance georgePerformance = new Performance();
        georgePerformance.setRun100m(LocalTime.of(0, 0, 27, 700));
        georgePerformance.setRun500m(LocalTime.of(0, 1, 34, 300));
        georgePerformance.setRun1000m(LocalTime.of(0, 3, 10));
        georgePerformance.setSwim100m(LocalTime.of(0, 2, 20));
        georgePerformance.setSwim500m(LocalTime.of(0, 11, 40));
        georgePerformance.setSwim1000m(null);
        georgePerformance.setBike10km(LocalTime.of(0, 32, 53));
        georgePerformance.setBike25km(LocalTime.of(1, 14, 32));
        performanceRepository.save(georgePerformance);
        return georgePerformance;
    }

    private Track getRunningTrack() {
        Track runningTrack = new Track();
        runningTrack.setName("Running Track");
        runningTrack.setImage(new Byte[]{0b01, 0b10, 0b11});
        trackRepository.save(runningTrack);
        return runningTrack;
    }

    private Track getBicycleTrack() {
        Track bicycleTrack = new Track();
        bicycleTrack.setName("Bicycle Track");
        bicycleTrack.setImage(new Byte[]{0b10, 0b01, 0b11});
        trackRepository.save(bicycleTrack);
        return bicycleTrack;
    }

    private Contest getRunningContest() {
        Contest runContest = new Contest();
        runContest.setName("Super Running!");
        runContest.setDate(LocalDate.of(2020, 7, 12));
        contestRepository.save(runContest);
        return runContest;
    }

    private Contest getBicycleContest() {
        Contest bikeContest = new Contest();
        bikeContest.setName("Super Bicycling!");
        bikeContest.setDate(LocalDate.of(2020, 7, 13));
        contestRepository.save(bikeContest);
        return bikeContest;
    }
}
