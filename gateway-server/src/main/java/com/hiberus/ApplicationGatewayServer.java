package com.hiberus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
public class ApplicationGatewayServer {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "gateway-server");
        SpringApplication.run(ApplicationGatewayServer.class, args);
    }
}
