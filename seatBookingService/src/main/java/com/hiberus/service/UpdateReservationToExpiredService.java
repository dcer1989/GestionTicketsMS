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
public class UpdateReservationToExpiredService {

    private final ReservationRepository reservationRepository;

    public void updateReservationToExpired(UUID reservationId) {

        reservationRepository.findById(reservationId)
                .ifPresentOrElse(reservation -> {

                    log.info("Updating reservation with ID: {} to EXPIRED.", reservationId);

                    reservation.setStatus(ReservationStatus.EXPIRED);
                    reservationRepository.save(reservation);
                }, () -> {
                    throw new ReservationNotFoundException(reservationId);
                });
    }
}