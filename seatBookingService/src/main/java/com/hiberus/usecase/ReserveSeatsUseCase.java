package com.hiberus.usecase;


import com.hiberus.exception.InactiveSeatException;
import com.hiberus.exception.SeatAlreadyReservedException;
import com.hiberus.exception.SeatsNotFoundException;
import com.hiberus.model.*;
import com.hiberus.repository.ReservationRepository;
import com.hiberus.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReserveSeatsUseCase {

    private final ReservationRepository reservationRepository;
    private final SeatsRepository seatsRepository;

public void reserveSeats(Reservation reservation) {

    log.info("Starting the seat reservation process for the reservation with show ID: {}", reservation.getShowId());
    List<Seat> seats = StreamSupport.stream(
            seatsRepository.findAllById(reservation.getSeatIds()).spliterator(), false
    ).toList();

    // Verificar si algún asiento no fue encontrado
    if (seats.size() != reservation.getSeatIds().size()) {
        log.error("Not all requested seats were found. Requested IDs: {}", reservation.getSeatIds());
        throw new SeatsNotFoundException();
    }

    for (Seat seat : seats) {
        log.info("Validating the seat with ID: {}", seat.getId());

        if (!seat.isActive()) {
            log.error("The seat with ID {} is inactive and cannot be reserved", seat.getId());
            throw new InactiveSeatException(seat.getId());
        }
        if (SeatStatus.UNAVAILABLE.equals(seat.getStatus())) {
            log.error("The seat with ID {} is already reserved", seat.getId());
            throw new SeatAlreadyReservedException(seat.getId());
        }
        seat.setStatus(SeatStatus.UNAVAILABLE);
    }

    log.info("Saving the updated state of the seats in the database");
    seatsRepository.saveAll(seats);

    reservation.setId(UUID.randomUUID());
    reservation.setReservationExpiresAt(LocalDateTime.now().plusMinutes(15));
    reservation.setStatus(ReservationStatus.ACTIVE);

    log.info("Saving the reservation with ID: {}", reservation.getId());
    reservationRepository.save(reservation);
}
}
