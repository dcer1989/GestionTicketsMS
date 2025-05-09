package com.hiberus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
public class ApplicationSeatBookingService {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "seatBookingService");
        SpringApplication.run(ApplicationSeatBookingService.class, args);
    }
}