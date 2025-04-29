package com.hiberus.mapper;

import com.hiberus.dto.SeatStatus;

public class SeatStatusMapper {

    // Convierte de SeatStatus a SeatStatusDto
    public static SeatStatus toDto(com.hiberus.model.SeatStatus seatStatus) {
        if (seatStatus == null) {
            return null;
        }
        return SeatStatus.valueOf(seatStatus.name());
    }
}