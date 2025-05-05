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
public class DeactivateSeatByIdUseCase {

    private final SeatsRepository seatsRepository;

    public void deactivateSeatById(UUID seatId) {

        log.info("Deactivating seat with ID: {}", seatId);

        Seat seat = seatsRepository.findById(seatId)
                        .orElseThrow(() -> new SeatNotFoundException(seatId));
        seat.setActive(false);
        seatsRepository.save(seat);
    }
}