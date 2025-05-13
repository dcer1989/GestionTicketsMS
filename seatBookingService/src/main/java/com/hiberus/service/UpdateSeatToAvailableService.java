package com.hiberus.service;

import com.hiberus.exception.SeatNotFoundException;
import com.hiberus.model.Seat;
import com.hiberus.model.SeatStatus;
import com.hiberus.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateSeatToAvailableService {

    private final SeatsRepository seatsRepository;

    public List<Seat> updateSeatsToAvailable(List<UUID> seatIds) {

        log.info("Updating the status of the seats to AVAILABLE for the IDs: {}", seatIds);

        List<Seat> seats = seatsRepository.findAllById(seatIds).stream()
                .toList();

        if (seats.isEmpty()) {
            throw new SeatNotFoundException();
        }

        seats.forEach(seat -> seat.setStatus(SeatStatus.AVAILABLE));

        return seats;
    }
}