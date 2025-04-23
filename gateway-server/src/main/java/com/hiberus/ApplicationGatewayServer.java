package com.hiberus;

import org.springframework.boot.SpringApplication;

public class ApplicationGatewayServer {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "gateway-server");
        SpringApplication.run(ApplicationGatewayServer.class, args);
    }
}
