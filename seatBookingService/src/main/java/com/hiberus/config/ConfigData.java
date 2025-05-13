package com.hiberus.config;

import com.hiberus.model.Seat;
import com.hiberus.model.SeatStatus;
import com.hiberus.repository.SeatsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Configuration
public class ConfigData {

    @Bean
    public CommandLineRunner initDatabase(SeatsRepository seatsRepository) {
        return args -> {
            List<Seat> seats = new ArrayList<>();
            String[] rows = {"A", "B", "C", "D"};

            for (int i = 1; i <= 19; i++) {
                Seat seat = new Seat();
                seat.setId(UUID.fromString(String.format("00000000-0000-0000-0000-%012d", i))); // UUID fijo
                seat.setRow(rows[(i - 1) % rows.length]);
                seat.setNumber(i);
                seat.setStatus(SeatStatus.AVAILABLE);
                seat.setActive(true);
                seats.add(seat);
            }

            Seat inactiveSeat = new Seat();
            inactiveSeat.setId(UUID.fromString("00000000-0000-0000-0000-000000000020")); // UUID fijo
            inactiveSeat.setRow(rows[19 % rows.length]);
            inactiveSeat.setNumber(20);
            inactiveSeat.setStatus(SeatStatus.UNAVAILABLE);
            inactiveSeat.setActive(false);
            seats.add(inactiveSeat);

            seatsRepository.saveAll(seats);
        };
    }
}