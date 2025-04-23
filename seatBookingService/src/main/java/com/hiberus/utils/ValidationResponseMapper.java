package com.hiberus.utils;

import com.hiberus.dto.ValidationResponseDto;

import java.util.List;
import java.util.UUID;

public class ValidationResponseMapper {

    public static ValidationResponseDto toDto(boolean valid, List<UUID> invalidSeats) {
        ValidationResponseDto dto = new ValidationResponseDto();
        dto.setValid(valid);
        dto.setInvalidSeats(invalidSeats);
        return dto;
    }
}