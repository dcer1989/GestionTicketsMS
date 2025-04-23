package com.hiberus.config;

import com.hiberus.models.Seat;
import com.hiberus.models.SeatStatus;
import com.hiberus.repository.SeatsServiceRepository;
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
    public CommandLineRunner initDatabase(SeatsServiceRepository seatsRepository) {
        return args -> {
            List<Seat> seats = new ArrayList<>();
            Random random = new Random();
            String[] rows = {"A", "B", "C", "D"};

            for (int i = 1; i <= 19; i++) {
                Seat seat = new Seat();
                seat.setId(UUID.randomUUID());
                seat.setRow(rows[random.nextInt(rows.length)]);
                seat.setNumber(i);
                seat.setStatus(SeatStatus.AVAILABLE);
                seat.setActive(true);
                seats.add(seat);
            }

            Seat inactiveSeat = new Seat();
            inactiveSeat.setId(UUID.randomUUID());
            inactiveSeat.setRow(rows[random.nextInt(rows.length)]);
            inactiveSeat.setNumber(20);
            inactiveSeat.setStatus(SeatStatus.AVAILABLE);
            inactiveSeat.setActive(false);
            seats.add(inactiveSeat);

            seatsRepository.saveAll(seats);
        };
    }
}