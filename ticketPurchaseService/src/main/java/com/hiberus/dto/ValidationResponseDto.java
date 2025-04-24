package com.hiberus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ValidationResponseDto {
    private boolean valid; // Indica si todos los asientos son válidos
    private List<UUID> invalidSeats; // Lista de IDs de los asientos no válidos
}