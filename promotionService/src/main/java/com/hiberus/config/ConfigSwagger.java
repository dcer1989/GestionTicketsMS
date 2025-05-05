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
                        .title("Cinema Promotion Service API")
                        .version("1.0")
                        .description("API para la gestión de promociones en entradas de cine."));
    }

    @Bean
    public GroupedOpenApi promotionsApi() {
        return GroupedOpenApi.builder()
                .group("promotionService")
                .pathsToMatch("/v1/promotions/**")
                .build();
    }
}