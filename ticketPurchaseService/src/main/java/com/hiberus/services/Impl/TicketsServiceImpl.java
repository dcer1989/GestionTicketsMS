package com.hiberus.services.Impl;

import com.hiberus.dto.TicketRequestDto;
import com.hiberus.dto.TicketResponseDto;
import com.hiberus.services.TicketsService;
import org.springframework.stereotype.Service;

@Service
public class TicketsServiceImpl implements TicketsService {

    @Override
    public TicketResponseDto purchaseTicket(TicketRequestDto ticketRequestDto) {
        // Implementación del método de compra de tickets
        return new TicketResponseDto();
    }
}