package com.hiberus.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConfigSwagger {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cinema Shows Management API")
                        .version("1.0")
                        .description("API para la gestión de shows y horarios en el cine."));
    }

    @Bean
    public GroupedOpenApi showsApi() {
        return GroupedOpenApi.builder()
                .group("showsService")
                .pathsToMatch("/v1/shows/**")
                .build();
    }
}
