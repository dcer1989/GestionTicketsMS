package com.hiberus.service;

import com.hiberus.exception.ReservationNotFoundException;
import com.hiberus.model.ReservationStatus;
import com.hiberus.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateReservationToCompletedService {

    private final ReservationRepository reservationRepository;

    public void updateReservationCompleted(UUID reservationId) {

        log.info("Starting the update of the reservation with ID: {} to COMPLETED status", reservationId);

        reservationRepository.findById(reservationId)
                .ifPresentOrElse(reservation -> {

                    if (reservation.isActive()) {
                        log.info("The reservation with ID: {} is active. Updating status to COMPLETED.", reservationId);

                        reservation.setStatus(ReservationStatus.COMPLETED);
                        reservationRepository.save(reservation);
                    } else {
                        log.warn("The reservation with ID: {} is not active. No status update was performed.", reservationId);
                    }

                }, () -> {
                    throw new ReservationNotFoundException(reservationId);
                });
    }
}