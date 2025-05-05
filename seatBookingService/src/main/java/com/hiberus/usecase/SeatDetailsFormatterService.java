package com.hiberus.usecase;

import com.hiberus.exception.SeatNotFoundException;
import com.hiberus.model.Seat;
import com.hiberus.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class SeatDetailsFormatterService {

    private final SeatsRepository seatsRepository;

    @Named("formatSeats")
    public List<String> formatSeatDetails(List<UUID> seatIds) {

        log.info("Mapping seat details to Row/Number format for seats with IDs: {}", seatIds);

        List<Seat> seats = StreamSupport.stream(seatsRepository.findAllById(seatIds).spliterator(), false)
                .toList();

        if (seats.isEmpty()) {
            throw new SeatNotFoundException();
        }

        return seats.stream()
                .map(seat -> String.format("Row: %s, Number: %d",
                        seat.getRow(), seat.getNumber()))
                .toList();
    }
}