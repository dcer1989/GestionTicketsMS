package com.hiberus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigServer
public class ApplicationConfigServer {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "native");
        SpringApplication.run(ApplicationConfigServer.class, args);
    }
}