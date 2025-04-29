package com.hiberus.mapper;

import com.hiberus.model.Seat;
import com.hiberus.dto.SeatResponse;

public class SeatMapper {

    // Convierte de Seat a SeatDto
    public static SeatResponse toDto(Seat seat) {
        if (seat == null) {
            return null;
        }
        SeatResponse dto = new SeatResponse();
        dto.setId(seat.getId());
        dto.setRow(seat.getRow());
        dto.setNumber(seat.getNumber());
        dto.setStatus(seat.getStatus() != null ? SeatStatusMapper.toDto(seat.getStatus()) : null);
        dto.setActive(seat.isActive());
        return dto;
    }
}