package com.hiberus.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReservationResponseDto {
    private List<String> seats; // Lista de asientos reservados (formato: "Fila, Número")
    private String reservedBy; // Nombre del usuario que realizó la reserva
    private LocalDateTime reservationExpiresAt; // Fecha y hora de expiración de la reserva
    private String status; // Estado de la reserva
}