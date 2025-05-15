package com.hiberus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationPromotionService {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "promotionService");
        SpringApplication.run(ApplicationPromotionService.class, args);
    }
}