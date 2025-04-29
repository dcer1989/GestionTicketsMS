package com.hiberus.usecase;

import com.hiberus.exception.SeatNotFoundByIdException;
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

//        if (!seatsRepository.existsById(seatId)) {
//            throw new SeatNotFoundException(seatId);
//        }

        Seat seat = seatsRepository.findById(seatId)
                        .orElseThrow(() -> new SeatNotFoundByIdException(seatId));
        seat.setActive(false);
        seatsRepository.save(seat);
    }
}