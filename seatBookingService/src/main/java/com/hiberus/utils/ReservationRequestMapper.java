package com.hiberus.utils;

import com.hiberus.dto.ReservationRequestDto;
import com.hiberus.models.Reservation;

public class ReservationRequestMapper {

    public static Reservation toEntity(ReservationRequestDto dto) {
        Reservation reservation = new Reservation();
        reservation.setSeatIds(dto.getSeatIds());
        reservation.setShowId(dto.getShowId());
        reservation.setShowtimeId(dto.getShowtimeId());
        reservation.setReservedBy(dto.getReservedBy());
        return reservation;
    }
}