package com.velikokhatko.study.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ContestTest {

    @Test
    public void businessKeyTest() {
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
        assertEquals(contestOne, contestTwo);

        contestOne.removeMember(userProfile);
        assertEquals(0, userProfile.getContests().size());
        assertEquals(contestOne, contestTwo);

        contestOne.setWinner(userProfile);
        assertEquals(contestOne, contestTwo);
    }

    @Test
    public void unsupportedOperationsTest() {
        assertThrows(UnsupportedOperationException.class, () -> new Contest().setMembers(new HashSet<>()));
        assertThrows(UnsupportedOperationException.class, () -> new Contest().getMembers().add(new UserProfile()));
    }
}