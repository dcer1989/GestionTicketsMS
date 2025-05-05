// TicketPurchaseRestController.java
    package com.hiberus.controller;

    import com.hiberus.dto.*;
    import com.hiberus.mapper.TicketResponseMapper;
    import com.hiberus.model.Ticket;
    import com.hiberus.usecase.CreateTicketUseCase;
    import com.hiberus.usecase.PromotionServiceFeign;
    import com.hiberus.usecase.SeatBookingServiceFeign;
    import com.hiberus.usecase.TicketPurchaseUseCase;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.*;

    import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TicketPurchaseRestController {

    private final TicketPurchaseUseCase ticketPurchaseUseCase;
    private final TicketResponseMapper ticketResponseMapper;

    @PostMapping("/v1/tickets/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse purchase(@RequestBody TicketRequest ticketRequest) {

        log.info("Request received to purchase ticket: {}", ticketRequest);

        return ticketResponseMapper.toDto(
                ticketPurchaseUseCase.purchaseTicket(
                        ticketRequest.reservationId(),
                        ticketRequest.promotionId()
                )
        );
    }
}