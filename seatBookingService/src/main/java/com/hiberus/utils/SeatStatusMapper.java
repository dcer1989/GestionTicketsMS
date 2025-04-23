package com.hiberus.utils;

import com.hiberus.models.SeatStatus;
import com.hiberus.dto.SeatStatusDto;

public class SeatStatusMapper {

    // Convierte de SeatStatus a SeatStatusDto
    public static SeatStatusDto toDto(SeatStatus seatStatus) {
        if (seatStatus == null) {
            return null;
        }
        return SeatStatusDto.valueOf(seatStatus.name());
    }
}