package com.velikokhatko.study.repository;

import com.velikokhatko.study.model.Performance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PerformanceRepositoryTest {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Test
    public void saveTest() {
        Performance performance = new Performance();
        performance.setRun100m(LocalTime.of(0, 0, 16, 98));
        performance.setBike10km(LocalTime.of(0, 35, 20));
        performance.setSwim100m(LocalTime.of(0, 3, 11));

        performanceRepository.save(performance);

        Iterator<Performance> all = performanceRepository.findAll().iterator();
        assertTrue(all.hasNext());
        Performance persistedPerformance = all.next();
        assertFalse(all.hasNext());

        assertEquals(performance, persistedPerformance);
    }
}