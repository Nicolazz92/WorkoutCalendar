package com.velikokhatko.study.repository;

import com.velikokhatko.study.model.Contest;
import com.velikokhatko.study.model.Track;
import com.velikokhatko.study.model.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        track.setImage(new Byte[]{0b11, 0b01});
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

        Iterator<Contest> contestIter = contestRepository.findAll().iterator();
        assertTrue(contestIter.hasNext());
        Contest persistedContest = contestIter.next();

        System.out.println(persistedContest);
        UserProfile persistedUserProfile = persistedContest.getMembers().stream().findFirst().orElse(new UserProfile());

        assertEquals(1, persistedContest.getMembers().size());
        assertEquals(userProfile, persistedUserProfile);
        assertEquals(persistedUserProfile.getContests().stream().findFirst().orElse(new Contest()), contest);
        assertEquals(track, persistedContest.getTrack());
        assertEquals(contest, persistedContest);
    }
}