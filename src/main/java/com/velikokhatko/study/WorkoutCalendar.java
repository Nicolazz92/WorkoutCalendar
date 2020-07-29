package com.velikokhatko.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class WorkoutCalendar {
    public static void main(String[] args) {
        SpringApplication.run(WorkoutCalendar.class, args);
    }
}
