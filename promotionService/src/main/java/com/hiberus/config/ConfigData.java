package com.hiberus.config;

import com.hiberus.model.Promotion;
import com.hiberus.repository.PromotionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Configuration
public class ConfigData {

    @Bean
    public CommandLineRunner initDatabase(PromotionRepository promotionRepository) {
        return args -> {
            // Crear promociones con UUIDs fijos
            Promotion promotion1 = new Promotion();
            promotion1.setPromotionId(UUID.fromString("88888888-8888-8888-8888-888888888888"));
            promotion1.setName("Descuento de Verano");
            promotion1.setDescription("10% de descuento en todas las entradas durante el verano");
            promotion1.setDiscountPercentage(10);
            promotion1.setValidFrom(Instant.parse("2023-06-01T00:00:00Z"));
            promotion1.setValidUntil(Instant.parse("2023-08-31T23:59:59Z"));

            Promotion promotion2 = new Promotion();
            promotion2.setPromotionId(UUID.fromString("99999999-9999-9999-9999-999999999999"));
            promotion2.setName("Promoción de Estreno");
            promotion2.setDescription("15% de descuento en entradas para películas estrenadas este mes");
            promotion2.setDiscountPercentage(15);
            promotion2.setValidFrom(Instant.parse("2023-12-01T00:00:00Z"));
            promotion2.setValidUntil(Instant.parse("2023-12-31T23:59:59Z"));

            Promotion promotion3 = new Promotion();
            promotion3.setPromotionId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
            promotion3.setName("Descuento Familiar");
            promotion3.setDescription("20% de descuento para compras de 4 o más entradas");
            promotion3.setDiscountPercentage(20);
            promotion3.setValidFrom(Instant.parse("2023-01-01T00:00:00Z"));
            promotion3.setValidUntil(Instant.parse("2023-12-31T23:59:59Z"));

            // Guardar promociones en la base de datos
            promotionRepository.saveAll(List.of(promotion1, promotion2, promotion3));
        };
    }
}