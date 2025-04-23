package com.hiberus.services;

import com.hiberus.dto.TicketRequestDto;
import com.hiberus.dto.TicketResponseDto;

import java.util.UUID;

public interface TicketsService {

    /**
     * Procesa la compra de un ticket.
     *
     * @param ticketRequestDto Datos de la solicitud de compra del ticket.
     * @return Respuesta con los detalles del ticket creado.
     */
    TicketResponseDto purchaseTicket(TicketRequestDto ticketRequestDto);
}