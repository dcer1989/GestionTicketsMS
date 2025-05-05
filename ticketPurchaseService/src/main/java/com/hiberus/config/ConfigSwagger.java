package com.hiberus.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSwagger {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cinema Ticket Management API")
                        .version("1.0")
                        .description("Reserve seats, create a ticket, and process payment in a single request."));
    }

    @Bean
    public GroupedOpenApi ticketsApi() {
        return GroupedOpenApi.builder()
                .group("ticketPurchaseService")
                .pathsToMatch("/v1/tickets/**")
                .build();
    }
}