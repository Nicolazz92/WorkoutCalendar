package com.velikokhatko.study.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("init-test-data")
public class InitTestData implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

    }
}
