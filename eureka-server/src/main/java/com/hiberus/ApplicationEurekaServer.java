package com.hiberus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaServer
public class ApplicationEurekaServer {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "eureka-server");
        SpringApplication.run(ApplicationEurekaServer.class, args);
    }
}