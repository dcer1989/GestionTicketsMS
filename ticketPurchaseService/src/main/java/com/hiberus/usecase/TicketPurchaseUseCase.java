package com.hiberus.usecase;

import com.hiberus.dto.ApplyPromotionRequest;
import com.hiberus.dto.ApplyPromotionResponse;
import com.hiberus.dto.ReservationByIdResponse;
import com.hiberus.exception.ReservationExpiredException;
import com.hiberus.model.ReservationStatus;
import com.hiberus.model.Ticket;
import com.hiberus.model.TicketStatus;
import com.hiberus.producer.TicketCreatedProducer;
import com.hiberus.service.CreateTicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketPurchaseUseCase {

    private final CreateTicketService createTicketService;
    private final PromotionServiceFeign promotionServiceFeign;
    private final SeatBookingServiceFeign seatBookingServiceFeign;
    private final TicketCreatedProducer ticketCreatedProducer;

    public Ticket purchaseTicket(UUID reservationId, UUID promotionId) {

        log.info("Starting ticket purchase process for reservation ID: {}", reservationId);

        Ticket ticket = createTicketService.createTicket(reservationId);

        log.info("Making a request to the promotions microservice with Promotion ID: {}", promotionId);

        ApplyPromotionRequest promotionRequest = new ApplyPromotionRequest(
                ticket.getId(),
                promotionId,
                ticket.getPrice()
        );
        ApplyPromotionResponse appliedPromotion = promotionServiceFeign.applyPromotion(promotionRequest);
        ticket.setPrice(appliedPromotion.finalPrice());

        log.info("Making a request to the seat booking microservice to check status of reservation with ID: {}", reservationId);

        ReservationByIdResponse reservationByIdResponse = seatBookingServiceFeign.getReservationById(reservationId);

        if (reservationByIdResponse.reservationStatus() == ReservationStatus.EXPIRED) {
            ticket.setStatus(TicketStatus.CANCELED);
            throw new ReservationExpiredException(reservationId);
        }

        log.info("Publishing ticket created event for Ticket with ID: {}", ticket.getId());

        ticketCreatedProducer.sendTicketCreatedMessage(ticket);

        return ticket;
    }
}