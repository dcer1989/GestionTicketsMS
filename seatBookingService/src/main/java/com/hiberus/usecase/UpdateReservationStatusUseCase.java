package com.hiberus.usecase;

import com.hiberus.exception.ReservationsNotFoundException;
import com.hiberus.exception.SeatsNotFoundException;
import com.hiberus.model.Reservation;
import com.hiberus.model.ReservationStatus;
import com.hiberus.model.Seat;
import com.hiberus.model.SeatStatus;
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
public class UpdateReservationStatusUseCase {

    private final ReservationRepository reservationRepository;
    private final SeatsRepository seatsRepository;

    public List<ReservationStatus> updateReservationStatus(List<UUID> reservationIds, boolean paymentSuccessful) {
        // Verificar si las reservas existen
        List<Reservation> reservations = StreamSupport.stream(
                reservationRepository.findAllById(reservationIds).spliterator(), false
        ).toList();

        if (reservations.isEmpty()) {
            throw new ReservationsNotFoundException(); // Excepción personalizada ya existente
        }

        for (Reservation reservation : reservations) {
            if (paymentSuccessful) {
                reservation.setStatus(ReservationStatus.COMPLETED);
            } else if (reservation.getReservationExpiresAt().isBefore(LocalDateTime.now())) {
                reservation.setStatus(ReservationStatus.EXPIRED);

                // Liberar los asientos asociados
                List<Seat> seats = StreamSupport.stream(seatsRepository.findAllById(reservation.getSeatIds()).spliterator(), false)
                        .toList();

                if (seats.isEmpty()) {
                    throw new SeatsNotFoundException(); // Excepción personalizada ya existente
                }

                for (Seat seat : seats) {
                    seat.setStatus(SeatStatus.AVAILABLE);
                }
                seatsRepository.saveAll(seats);
            }
        }
        reservationRepository.saveAll(reservations);

        // Devolver los estados de las reservas actualizadas
        return reservations.stream()
                .map(Reservation::getStatus)
                .toList();
    }
}