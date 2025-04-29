package com.hiberus.usecase;

import com.hiberus.model.Seat;
import com.hiberus.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllSeatsUseCase {

    private final SeatsRepository seatsRepository;

    public List<Seat> getAllSeats() {

        log.info("Use case to get all Seats");

        return seatsRepository.findAll();
    }
}
