package com.hiberus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationShowsService {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "showsService");
        SpringApplication.run(ApplicationShowsService.class, args);
    }
}