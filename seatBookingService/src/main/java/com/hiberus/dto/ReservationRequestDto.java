package com.hiberus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ReservationRequestDto {
    private List<UUID> seatIds; // Lista de IDs de los asientos a reservar
    private UUID showId;        // ID del show para el cual se reservan los asientos
    private Integer showtimeId; // ID del horario del show
    private String reservedBy;  // Nombre del usuario que realiza la reserva
}