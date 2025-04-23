package com.hiberus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApplicationShowsService {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "showsService");
        SpringApplication.run(ApplicationShowsService.class, args);
    }
}