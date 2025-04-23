package com.hiberus.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSwagger {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cinema Seat Management API")
                        .version("1.0")
                        .description("API para la gestión de asientos en salas de cine"));
    }

    @Bean
    public GroupedOpenApi seatBookingApi() {
        return GroupedOpenApi.builder()
                .group("seatBookingService")
                .pathsToMatch("/v1/seats/**")
                .build();
    }
}