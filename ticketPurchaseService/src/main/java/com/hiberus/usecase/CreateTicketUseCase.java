package com.hiberus.usecase;

import com.hiberus.dto.TicketRequest;
import com.hiberus.exception.InvalidReservationException;
import com.hiberus.model.Ticket;
import com.hiberus.model.TicketStatus;
import com.hiberus.repository.TicketPurchaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateTicketUseCase {

    private final TicketPurchaseRepository ticketPurchaseRepository;

    public Ticket createTicket(UUID reservationId) {

        if (reservationId == null) {
            throw new InvalidReservationException();
        }

        log.info("Creating ticket for reservation ID: {}", reservationId);

        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID());
        ticket.setReservationId(reservationId);
        ticket.setPrice(8.0); // Assuming a fixed price for simplicity
        ticket.setStatus(TicketStatus.PENDING);

        log.info("Saving ticket with ID: {}", ticket.getId());

        return ticketPurchaseRepository.save(ticket);
    }
}