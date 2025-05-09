package com.hiberus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApplicationTicketPurchaseService {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "ticketPurchaseService");
        SpringApplication.run(ApplicationTicketPurchaseService.class, args);
    }
}