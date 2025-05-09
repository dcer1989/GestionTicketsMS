package com.hiberus.usecase;

import com.hiberus.exception.ReservationNotFoundException;
import com.hiberus.model.Reservation;
import com.hiberus.model.ReservationStatus;
import com.hiberus.repository.ReservationRepository;
import com.hiberus.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateReservationCompletedUseCase {

    private final ReservationRepository reservationRepository;
    private final SeatsRepository seatsRepository;

    public ReservationStatus updateReservationCompleted(UUID reservationId, boolean paymentSuccessful) {

        log.info("Starting update of reservation with ID: {} to COMPLETED status", reservationId);

        return reservationRepository.findById(reservationId)
                .map(reservation -> {

                    if (paymentSuccessful && !reservation.isCompleted()) {

                        log.info("Payment successful for reservation ID: {}. Updating status to COMPLETED.", reservationId);

                        reservation.setStatus(ReservationStatus.COMPLETED);
                        reservationRepository.save(reservation);
                    }

                    return reservation.getStatus();
                })
                .orElseThrow(ReservationNotFoundException::new);
    }
}