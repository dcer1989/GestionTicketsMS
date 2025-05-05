package com.hiberus.usecase;


import com.hiberus.exception.InactiveSeatException;
import com.hiberus.exception.SeatAlreadyReservedException;
import com.hiberus.exception.SeatNotFoundException;
import com.hiberus.model.*;
import com.hiberus.repository.ReservationRepository;
import com.hiberus.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    public Reservation reserveSeats(Reservation reservation) {

        log.info("Starting the seat reservation process for the reservation with show ID: {}", reservation.getShowId());
        List<Seat> seats = StreamSupport.stream(
                seatsRepository.findAllById(reservation.getSeatIds()).spliterator(), false
        ).toList();

        // Verificar si algún asiento no fue encontrado
        if (seats.size() != reservation.getSeatIds().size()) {
            throw new SeatNotFoundException();
        }

        for (Seat seat : seats) {

            if (!seat.isActive()) {
                throw new InactiveSeatException(seat.getId());
            }
            if (SeatStatus.UNAVAILABLE.equals(seat.getStatus())) {
                throw new SeatAlreadyReservedException(seat.getId());
            }
            seat.setStatus(SeatStatus.UNAVAILABLE);
        }

        log.info("Saving the updated state of the seats in the database");

        seatsRepository.saveAll(seats);

        reservation.setId(UUID.randomUUID());
        reservation.setReservationExpiresAt(Instant.now().plusSeconds(900));
        reservation.setStatus(ReservationStatus.ACTIVE);

        log.info("Saving the reservation with ID: {}", reservation.getId());

        return reservationRepository.save(reservation);
    }
}
