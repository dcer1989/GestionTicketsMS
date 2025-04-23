package com.hiberus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TicketResponseDto {
    private UUID id; // ID único del ticket
    private List<String> seats; // Lista de asientos reservados (formato: "Sala, Fila, Número")
    private String reservedBy; // Nombre del usuario que realizó la reserva
    private String showTitle; // Título del show
    private String showtime; // Horario del show
    private double price; // Precio del ticket
    private String status; // Estado del ticket (PURCHASED, CANCELED)
}