package com.hiberus.usecase;

import com.hiberus.model.Reservation;
import com.hiberus.model.ReservationStatus;
import com.hiberus.repository.SeatsRepository;
import com.hiberus.service.UpdateSeatToUnavailableService;
import com.hiberus.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReserveSeatsUseCase {

    private final ReservationRepository reservationRepository;
    private final SeatsRepository seatsRepository;
    private final UpdateSeatToUnavailableService updateSeatToUnavailableService;

    public Reservation reserveSeats(Reservation reservation) {

        log.info("Starting the seat reservation process for the reservation with show ID: {}", reservation.getShowId());

        // Actualizar asientos a UNAVAILABLE
        List<UUID> seatIds = reservation.getSeatIds();
        seatsRepository.saveAll(updateSeatToUnavailableService.updateSeatsToUnavailable(seatIds));

        reservation.setId(UUID.randomUUID());
        reservation.setReservationExpiresAt(Instant.now().plusSeconds(900)); // 900 para 15 minutos
        reservation.setStatus(ReservationStatus.ACTIVE);

        log.info("Saving the reservation with ID: {}", reservation.getId());

        return reservationRepository.save(reservation);
    }
}