package com.hiberus.usecase;

import com.hiberus.dto.ApplyPromotionRequest;
import com.hiberus.dto.ApplyPromotionResponse;
import com.hiberus.dto.UpdateRequest;
import com.hiberus.dto.UpdateResponse;
import com.hiberus.exception.ReservationExpiredException;
import com.hiberus.model.ReservationStatus;
import com.hiberus.model.Ticket;
import com.hiberus.model.TicketStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketPurchaseUseCase {

    private final CreateTicketUseCase createTicketUseCase;
    private final PromotionServiceFeign promotionServiceFeign;
    private final SeatBookingServiceFeign seatBookingServiceFeign;

    public Ticket purchaseTicket(UUID reservationId, UUID promotionId) {

        log.info("Starting ticket purchase process for reservation ID: {}", reservationId);

        Ticket ticket = createTicketUseCase.createTicket(reservationId);

        ApplyPromotionRequest promotionRequest = new ApplyPromotionRequest(
                ticket.getId(),
                promotionId,
                ticket.getPrice()
        );
        ApplyPromotionResponse appliedPromotion = promotionServiceFeign.applyPromotion(promotionRequest);
        ticket.setPrice(appliedPromotion.finalPrice());

        log.info("Promotion applied successfully: {}", appliedPromotion);

        UpdateRequest updateRequest = new UpdateRequest(reservationId);
        UpdateResponse updateResponse = seatBookingServiceFeign.updateReservationStatus(updateRequest);

        if (updateResponse.reservationStatus() == ReservationStatus.EXPIRED) {
            ticket.setStatus(TicketStatus.CANCELED);
            throw new ReservationExpiredException(reservationId);
        }

        log.info("Reservation status updated successfully: {}", updateResponse);

        return ticket;
    }
}