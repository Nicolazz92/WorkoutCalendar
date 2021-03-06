package com.velikokhatko.study.repository;

import com.velikokhatko.study.model.Contest;
import com.velikokhatko.study.model.Track;
import com.velikokhatko.study.model.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class ContestRepositoryTest {
    @Autowired
    private ContestRepository contestRepository;
    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Test
    public void saveTest() {
        Track track = new Track();
        track.setName("testTrack");
        trackRepository.save(track);

        UserProfile userProfile = new UserProfile();
        userProfile.setName("testUser");
        userProfileRepository.save(userProfile);

        Contest contest = new Contest();
        contest.setDate(LocalDate.now());
        contest.setName("testContest");
        contest.setTrack(track);
        contest.addMember(userProfile);
        contestRepository.save(contest);

        assertNotNull(contest.getId());
        assertEquals(userProfile, contest.getMembers().stream().findAny().orElse(new UserProfile()));
        assertEquals(contest, userProfile.getContests().stream().findFirst().orElse(new Contest()));
        assertEquals(track, contest.getTrack());
    }
}