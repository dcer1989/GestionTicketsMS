package com.hiberus.utils;

import com.hiberus.dto.ReservationResponseDto;
import com.hiberus.models.Seat;
import com.hiberus.models.Reservation;
import com.hiberus.models.ReservationResult;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationResponseMapper {

public static ReservationResponseDto toDto(ReservationResult reservationResult) {
    Reservation reservation = reservationResult.getReservation();
    List<Seat> seats = reservationResult.getSeats();
    ReservationResponseDto dto = new ReservationResponseDto();
    dto.setSeats(seats.stream()
            .map(seat -> "Fila: " + seat.getRow() + ", Número: " + seat.getNumber())
            .collect(Collectors.toList()));
    dto.setReservedBy(reservation.getReservedBy());
    dto.setReservationExpiresAt(reservation.getReservationExpiresAt());
    dto.setStatus(reservation.getStatus().name());
    return dto;
}
}