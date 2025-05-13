package com.hiberus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;

import java.net.InetAddress;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigServer
public class ApplicationConfigServer {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "native");
        SpringApplication.run(ApplicationConfigServer.class, args);

        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            System.out.println("📡 IP local detectada: " + ip);
        } catch (java.net.UnknownHostException e) {
            System.err.println("Error al obtener la dirección IP local: " + e.getMessage());
        }
    }
}