package com.velikokhatko.study.repository;

import com.velikokhatko.study.model.Track;
import com.velikokhatko.study.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TrackRepositoryTest {
    String picturePath = "image/spring.jpeg";

    @Autowired
    private TrackRepository trackRepository;

    @Test
    public void saveTest() throws IOException {
        Track track = new Track();
        track.setName("testTrack");
        Byte[] objectBytes = TestUtils.getBytes(picturePath);
        assertEquals(4359, objectBytes.length);
        track.setImage(objectBytes);

        trackRepository.save(track);

        Iterator<Track> all = trackRepository.findAll().iterator();
        assertTrue(all.hasNext());
        Track persistedTrack = all.next();
        assertEquals(4359, persistedTrack.getImage().length);
        assertFalse(all.hasNext());

        assertEquals(track, persistedTrack);
    }
}