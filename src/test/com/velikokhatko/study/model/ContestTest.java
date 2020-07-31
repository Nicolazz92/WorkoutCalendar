package com.velikokhatko.study.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ContestTest {

    @Test
    public void equalsTest() {
        Contest contestOne = new Contest();
        contestOne.setDate(LocalDate.now());
        contestOne.setName("contest");

        Contest contestTwo = new Contest();
        contestTwo.setDate(LocalDate.now());
        assertNotEquals(contestOne, contestTwo);

        contestTwo.setName("contest");
        contestTwo.setId(111L);
        assertEquals(contestOne, contestTwo);

        contestOne.setTrack(new Track());
        assertNotEquals(contestOne, contestTwo);
        contestTwo.setTrack(new Track());
        assertEquals(contestOne, contestTwo);

        UserProfile userProfile = new UserProfile();

        contestOne.addMember(userProfile);
        assertEquals(userProfile.getContests().stream().findFirst().orElse(new Contest()), contestOne);
        assertNotEquals(contestOne, contestTwo);
        contestTwo.addMember(userProfile);
        assertEquals(contestOne, contestTwo);

        contestOne.removeMember(userProfile);
        assertEquals(0, userProfile.getContests().size());
        assertNotEquals(contestOne, contestTwo);
        contestTwo.removeMember(userProfile);
        assertEquals(contestOne, contestTwo);

        contestOne.setWinner(userProfile);
        assertNotEquals(contestOne, contestTwo);
        contestTwo.setWinner(userProfile);
        assertEquals(contestOne, contestTwo);

        assertThrows(UnsupportedOperationException.class, () -> contestOne.setMembers(new HashSet<>()));
        assertThrows(UnsupportedOperationException.class, () -> contestOne.getMembers().add(userProfile));
    }
}