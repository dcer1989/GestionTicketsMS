package com.hiberus.mapper;

import com.hiberus.dto.ReservationRequest;
import com.hiberus.model.Reservation;

public class ReservationRequestMapper {

    public static Reservation toEntity(ReservationRequest dto) {
        Reservation reservation = new Reservation();
        reservation.setSeatIds(dto.getSeatIds());
        reservation.setShowId(dto.getShowId());
        reservation.setShowtimeId(dto.getShowtimeId());
        reservation.setReservedBy(dto.getReservedBy());
        return reservation;
    }
}