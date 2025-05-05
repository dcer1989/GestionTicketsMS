package com.hiberus.usecase;

import com.hiberus.exception.SeatNotFoundException;
import com.hiberus.model.Seat;
import com.hiberus.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetSeatByIdUseCase {

    private final SeatsRepository seatsRepository;

    public Seat getSeatById(UUID seatId) {

        log.info("Getting seat with ID: {}", seatId);

        return seatsRepository.findById(seatId)
                .orElseThrow(() -> new SeatNotFoundException(seatId));
    }
}
