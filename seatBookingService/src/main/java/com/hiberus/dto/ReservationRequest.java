package com.hiberus.dto;

import java.util.List;
import java.util.UUID;

public record ReservationRequest(
    List<UUID> seatIds, // Lista de IDs de los asientos a reservar
    UUID showId,        // ID del show para el cual se reservan los asientos
    UUID showtimeId, // ID del horario del show
    String reservedBy   // Nombre del usuario que realiza la reserva
) {}