package com.hiberus.service;

import com.hiberus.exception.InactiveSeatException;
import com.hiberus.exception.SeatAlreadyReservedException;
import com.hiberus.exception.SeatNotFoundException;
import com.hiberus.model.Seat;
import com.hiberus.model.SeatStatus;
import com.hiberus.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateSeatToUnavailableService {

    private final SeatsRepository seatsRepository;

    public List<Seat> updateSeatsToUnavailable(List<UUID> seatIds) {

        log.info("Updating the status of the seats to UNAVAILABLE for the IDs: {}", seatIds);

        List<Seat> seats = seatsRepository.findAllById(seatIds).stream()
                .toList();

        if (seats.isEmpty() || seats.size() != seatIds.size()) {
            throw new SeatNotFoundException();
        }

        seats.forEach(seat -> {
            if (!seat.isActive()) {
                throw new InactiveSeatException(seat.getId());
            }
            if (!seat.isAvailable()) {
                throw new SeatAlreadyReservedException(seat.getId());
            }
            seat.setStatus(SeatStatus.UNAVAILABLE);
        });

        return seats;
    }
}