package com.hiberus.config;

import com.hiberus.models.Shows;
import com.hiberus.models.Showtime;
import com.hiberus.repository.ShowsServiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Configuration
public class ConfigData {

    @Bean
    public CommandLineRunner initDatabase(ShowsServiceRepository showsServiceRepository) {
        return args -> {
            // Crear showtimes para los shows
            Showtime showtime1 = new Showtime();
            showtime1.setRoom("Room 1");
            showtime1.setTimes("10:00:00");

            Showtime showtime2 = new Showtime();
            showtime2.setRoom("Room 2");
            showtime2.setTimes("12:00:00");

            Showtime showtime3 = new Showtime();
            showtime3.setRoom("Room 3");
            showtime3.setTimes("11:00:00");

            Showtime showtime4 = new Showtime();
            showtime4.setRoom("Room 2");
            showtime4.setTimes("14:00:00");

            // Crear shows con sus respectivos showtimes
            Shows show1 = Shows.builder()
                    .id(UUID.randomUUID())
                    .title("Jaws")
                    .description("Horror film about a shark")
                    .showtimes(List.of(showtime1))
                    .build();

            Shows show2 = Shows.builder()
                    .id(UUID.randomUUID())
                    .title("Blade Runner")
                    .description("Sic-fi film about replicants")
                    .showtimes(List.of(showtime2, showtime4))
                    .build();

            Shows show3 = Shows.builder()
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
