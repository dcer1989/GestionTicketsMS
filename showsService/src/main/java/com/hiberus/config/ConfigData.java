package com.hiberus.config;

import com.hiberus.models.Show;
import com.hiberus.models.Showtime;
import com.hiberus.repository.ShowsServiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class ConfigData {

    @Bean
    public CommandLineRunner initDatabase(ShowsServiceRepository showsServiceRepository) {
        return args -> {
            AtomicInteger idGenerator = new AtomicInteger(1); // Generador de IDs

            // Crear showtimes con IDs autogenerados
            Showtime showtime1 = new Showtime();
            showtime1.setId(idGenerator.getAndIncrement());
            showtime1.setRoom("Room 1");
            showtime1.setTime("10:00:00");

            Showtime showtime2 = new Showtime();
            showtime2.setId(idGenerator.getAndIncrement());
            showtime2.setRoom("Room 2");
            showtime2.setTime("12:00:00");

            Showtime showtime3 = new Showtime();
            showtime3.setId(idGenerator.getAndIncrement());
            showtime3.setRoom("Room 3");
            showtime3.setTime("11:00:00");

            Showtime showtime4 = new Showtime();
            showtime4.setId(idGenerator.getAndIncrement());
            showtime4.setRoom("Room 2");
            showtime4.setTime("14:00:00");

            // Crear shows con sus respectivos showtimes
            Show show1 = Show.builder()
                    .id(UUID.randomUUID())
                    .title("Jaws")
                    .description("Horror film about a shark")
                    .showtimes(List.of(showtime1))
                    .build();

            Show show2 = Show.builder()
                    .id(UUID.randomUUID())
                    .title("Blade Runner")
                    .description("Sic-fi film about replicants")
                    .showtimes(List.of(showtime2, showtime4))
                    .build();

            Show show3 = Show.builder()
                    .id(UUID.randomUUID())
                    .title("Star Wars")
                    .description("Sic-fi film about space")
                    .showtimes(List.of(showtime3))
                    .build();

            // Guardar los shows en la base de datos
            showsServiceRepository.saveAll(Arrays.asList(show1, show2, show3));
        };
    }
}
