package com.tj.edu.practice4.test;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestCodeApplication.class, args);

//        SpringApplication application = new SpringApplication(TestCodeApplication.class);
//        application.setBannerMode(Banner.Mode.OFF);
//        application.run(args);
    }
}