package com.hiberus.services;

import com.hiberus.models.Reservation;
import com.hiberus.models.ReservationResult;
import com.hiberus.models.Seat;

import java.util.List;
import java.util.UUID;

public interface SeatsService {
    List<Seat> getSeats();
    Seat getSeatById(UUID id);
    void deactivateSeatById(UUID id);
    ReservationResult reserveSeats(Reservation reservation);
    boolean validateSeats(List<UUID> seatIds);
    List<UUID> getInvalidSeats(List<UUID> seatIds);
}