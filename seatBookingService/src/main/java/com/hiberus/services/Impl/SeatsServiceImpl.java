package com.hiberus.services.Impl;

import com.hiberus.models.Reservation;
import com.hiberus.models.Seat;
import com.hiberus.models.SeatStatus;
import com.hiberus.models.ReservationStatus;
import com.hiberus.models.ReservationResult;
import com.hiberus.repository.SeatsServiceRepository;
import com.hiberus.repository.ReservationRepository;
import com.hiberus.services.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class SeatsServiceImpl implements SeatsService {

    @Autowired
    private SeatsServiceRepository seatsRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Seat> getSeats() {
        return seatsRepository.findAll();
    }

    @Override
    public Seat getSeatById(UUID id) {
        return seatsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found with ID: " + id));
    }

    @Override
    public void deactivateSeatById(UUID id) {
        Seat seat = getSeatById(id);
        seat.setActive(false);
        seatsRepository.save(seat);
    }

    @Override
    public ReservationResult reserveSeats(Reservation reservation) {
        List<Seat> seats = StreamSupport.stream(
                seatsRepository.findAllById(reservation.getSeatIds()).spliterator(), false
        ).toList();

        if (seats.isEmpty() || seats.size() != reservation.getSeatIds().size()) {
            throw new RuntimeException("Uno o más asientos no fueron encontrados");
        }

        for (Seat seat : seats) {
            if (!seat.isActive()) {
                throw new RuntimeException("El asiento " + seat.getId() + " está inactivo y no puede ser reservado");
            }
            if (seat.getStatus() != null && !seat.getStatus().equals(SeatStatus.AVAILABLE)) {
                throw new RuntimeException("El asiento " + seat.getId() + " no está disponible para reserva");
            }
            seat.setStatus(SeatStatus.UNAVAILABLE);
        }

        seatsRepository.saveAll(seats);

        reservation.setId(UUID.randomUUID());
        reservation.setReservationExpiresAt(LocalDateTime.now().plusMinutes(15));
        reservation.setStatus(ReservationStatus.ACTIVE);

        Reservation savedReservation = reservationRepository.save(reservation);

        ReservationResult result = new ReservationResult();
        result.setReservation(savedReservation);
        result.setSeats(seats);

        return result;
    }

    @Override
    public boolean validateSeats(List<UUID> seatIds) {
        List<Seat> seats = StreamSupport.stream(
            seatsRepository.findAllById(seatIds).spliterator(), false
        ).toList();

        for (Seat seat : seats) {
            if (!seat.isActive() || seat.getStatus() == null) {
                return false;
            }

            reservationRepository.findBySeatIdsContaining(seat.getId()).ifPresent(reservation -> {
                if (reservation.getStatus() == ReservationStatus.ACTIVE &&
                    reservation.getReservationExpiresAt().isBefore(LocalDateTime.now())) {
                    // Cambiar el estado de la reserva a expirada
                    reservation.setStatus(ReservationStatus.EXPIRED);
                    reservationRepository.save(reservation);

                    // Cambiar el estado del asiento a disponible
                    seat.setStatus(SeatStatus.AVAILABLE);
                    seatsRepository.save(seat);
                } else if (reservation.getStatus() == ReservationStatus.ACTIVE) {
                    // Si la reserva sigue activa, el asiento debe permanecer no disponible
                    seat.setStatus(SeatStatus.UNAVAILABLE);
                    seatsRepository.save(seat);
                }
            });

            // Validar que el asiento esté en un estado válido después de procesar la reserva
            if (seat.getStatus().equals(SeatStatus.UNAVAILABLE)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<UUID> getInvalidSeats(List<UUID> seatIds) {
        return seatIds.stream()
                .filter(id -> {
                    Seat seat = seatsRepository.findById(id).orElse(null);
                    return seat == null || !seat.isActive() || seat.getStatus() == null || seat.getStatus().equals(SeatStatus.UNAVAILABLE);
                })
                .toList();
    }
}