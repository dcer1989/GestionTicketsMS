package com.hiberus.scheduler;

import com.hiberus.model.Reservation;
import com.hiberus.model.ReservationStatus;
import com.hiberus.model.Seat;
import com.hiberus.repository.ReservationRepository;
import com.hiberus.repository.SeatsRepository;
import com.hiberus.service.UpdateSeatToAvailableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReservationScheduler {

    private final ReservationRepository reservationRepository;
    private final SeatsRepository seatsRepository;
    private final UpdateSeatToAvailableService updateSeatToAvailableService;

    @Scheduled(fixedRate = 60000) // Ejecuta cada 60 segundos
    public void checkExpiredReservations() {

        log.info("Starting verification of expired reservations...");

        List<Reservation> reservations = reservationRepository.findAll();
        List<Seat> seatsToUpdate = new ArrayList<>();
        List<Reservation> reservationsToUpdate = new ArrayList<>();

        reservations.stream()
            .filter(Reservation::isExpired)
            .peek(reservation -> {

                log.info("The reservation with ID: {} has expired. Updating status and releasing seats.", reservation.getId());

                reservation.setStatus(ReservationStatus.EXPIRED);
                reservationsToUpdate.add(reservation);
            })
            .flatMap(reservation -> updateSeatToAvailableService.updateSeatsToAvailable(reservation.getSeatIds()).stream())
            .forEach(seatsToUpdate::add);

        if (!seatsToUpdate.isEmpty()) {
            seatsRepository.saveAll(seatsToUpdate);
        }

        if (!reservationsToUpdate.isEmpty()) {
            reservationRepository.saveAll(reservationsToUpdate);
        }

        log.info("Verification of expired reservations completed.");
    }
}