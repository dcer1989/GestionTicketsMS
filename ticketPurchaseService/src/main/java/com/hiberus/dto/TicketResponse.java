package com.hiberus.dto;

import java.util.List;
import java.util.UUID;

public record TicketResponse(
        UUID id, // ID único del ticket
        UUID reservationId, // Nombre del usuario que realizó la reserva
        double finalPrice, // Precio del ticket
        String status // Estado del ticket (PURCHASED, CANCELED)
) {}