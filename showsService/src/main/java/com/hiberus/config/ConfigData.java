package com.hiberus.config;

import com.hiberus.model.Show;
import com.hiberus.model.Showtime;
import com.hiberus.repository.ShowsRepository;
import com.hiberus.repository.ShowtimeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Configuration
public class ConfigData {

    @Bean
    public CommandLineRunner initDatabase(ShowsRepository showsRepository, ShowtimeRepository showtimeRepository) {
        return args -> {
            // Crear shows
            Show show1 = new Show();
            show1.setId(UUID.randomUUID());
            show1.setTitle("Jaws");
            show1.setDescription("Horror film about a shark");

            Show show2 = new Show();
            show2.setId(UUID.randomUUID());
            show2.setTitle("Blade Runner");
            show2.setDescription("Sci-fi film about replicants");

            Show show3 = new Show();
            show3.setId(UUID.randomUUID());
            show3.setTitle("Star Wars");
            show3.setDescription("Sci-fi film about space");

            // Guardar shows en la base de datos
            showsRepository.saveAll(List.of(show1, show2, show3));

            // Crear y asociar showtimes
            Showtime showtime1 = new Showtime();
            showtime1.setId(UUID.randomUUID());
            showtime1.setRoom("Room 1");
            showtime1.setTime(Instant.parse("2023-12-01T10:00:00Z"));
            showtime1.setShow(show1);

            Showtime showtime2 = new Showtime();
            showtime2.setId(UUID.randomUUID());
            showtime2.setRoom("Room 2");
            showtime2.setTime(Instant.parse("2023-12-01T12:00:00Z"));
            showtime2.setShow(show2);

            Showtime showtime3 = new Showtime();
            showtime3.setId(UUID.randomUUID());
            showtime3.setRoom("Room 3");
            showtime3.setTime(Instant.parse("2023-12-01T11:00:00Z"));
            showtime3.setShow(show3);

            Showtime showtime4 = new Showtime();
            showtime4.setId(UUID.randomUUID());
            showtime4.setRoom("Room 2");
            showtime4.setTime(Instant.parse("2023-12-01T14:00:00Z"));
            showtime4.setShow(show2);

            // Guardar showtimes en la base de datos
            showtimeRepository.saveAll(List.of(showtime1, showtime2, showtime3, showtime4));
        };
    }
}