package com.hiberus.usecase;

import com.hiberus.model.Reservation;
import com.hiberus.model.ReservationStatus;
import com.hiberus.model.Seat;
import com.hiberus.repository.ReservationRepository;
import com.hiberus.repository.SeatsRepository;
import com.hiberus.service.UpdateReservationToExpiredService;
import com.hiberus.service.UpdateSeatToAvailableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateReservationExpiredUseCase {

    private final ReservationRepository reservationRepository;
    private final SeatsRepository seatsRepository;
    private final UpdateSeatToAvailableService updateSeatToAvailableService;
    private final UpdateReservationToExpiredService updateReservationToExpiredService;

    public void updateReservationExpired() {

        log.info("Starting verification of expired reservations...");

        List<Reservation> activeReservations = reservationRepository.findByStatus(ReservationStatus.ACTIVE);
        List<Seat> seatsToUpdate = new ArrayList<>();

        activeReservations.stream()
            .filter(Reservation::isExpired)
            .peek(reservation -> {

                log.info("The reservation with ID: {} has expired. Releasing seats.", reservation.getId());

                updateReservationToExpiredService.updateReservationToExpired(reservation.getId());
            })
            .flatMap(reservation -> updateSeatToAvailableService.updateSeatsToAvailable(reservation.getSeatIds()).stream())
            .forEach(seatsToUpdate::add);

        if (!seatsToUpdate.isEmpty()) {
            seatsRepository.saveAll(seatsToUpdate);
        }

        log.info("Verification of expired reservations completed.");
    }
}