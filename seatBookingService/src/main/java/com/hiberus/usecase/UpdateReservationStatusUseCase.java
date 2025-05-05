package com.hiberus.usecase;

import com.hiberus.exception.ReservationNotFoundException;
import com.hiberus.exception.SeatNotFoundException;
import com.hiberus.model.Reservation;
import com.hiberus.model.ReservationStatus;
import com.hiberus.model.Seat;
import com.hiberus.model.SeatStatus;
import com.hiberus.repository.ReservationRepository;
import com.hiberus.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateReservationStatusUseCase {

    private final ReservationRepository reservationRepository;
    private final SeatsRepository seatsRepository;

    public ReservationStatus updateReservationStatus(UUID reservationId, boolean paymentSuccessful) {

        log.info("Starting update of reservation status for reservation ID: {}", reservationId);

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);


        log.info("Reservation expires at: {}. Current time: {}", reservation.getReservationExpiresAt(), Instant.now());

        if (paymentSuccessful) {

            log.info("Payment successful for reservation ID: {}. Updating status to COMPLETED.", reservationId);

            reservation.setStatus(ReservationStatus.COMPLETED);
        } else if (reservation.getReservationExpiresAt().isBefore(Instant.now())) {

            log.info("Reservation ID: {} has expired. Updating status to EXPIRED.", reservationId);

            reservation.setStatus(ReservationStatus.EXPIRED);

            List<Seat> seats = StreamSupport.stream(seatsRepository.findAllById(reservation.getSeatIds()).spliterator(), false)
                    .toList();

            if (seats.isEmpty()) {
                throw new SeatNotFoundException();
            }

            for (Seat seat : seats) {
                seat.setStatus(SeatStatus.AVAILABLE);
            }

            log.info("Seats released successfully for reservation ID: {}", reservationId);

            seatsRepository.saveAll(seats);
        }

        reservationRepository.save(reservation);

        return reservation.getStatus();
    }
}