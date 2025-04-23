package com.hiberus.utils;

import com.hiberus.models.Seat;
import com.hiberus.dto.SeatResponseDto;

public class SeatMapper {

    // Convierte de Seat a SeatDto
    public static SeatResponseDto toDto(Seat seat) {
        if (seat == null) {
            return null;
        }
        SeatResponseDto dto = new SeatResponseDto();
        dto.setId(seat.getId());
        dto.setRow(seat.getRow());
        dto.setNumber(seat.getNumber());
        dto.setStatus(seat.getStatus() != null ? SeatStatusMapper.toDto(seat.getStatus()) : null);
        dto.setActive(seat.isActive());
        return dto;
    }
}