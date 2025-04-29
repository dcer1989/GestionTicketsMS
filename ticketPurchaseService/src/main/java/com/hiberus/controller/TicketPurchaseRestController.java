package com.hiberus.controller;

import com.hiberus.dto.TicketRequestDto;
import com.hiberus.dto.TicketResponseDto;
import com.hiberus.dto.ValidationRequestDto;
import com.hiberus.dto.ValidationResponseDto;
import com.hiberus.service.SeatBookingServiceFeign;
import com.hiberus.service.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/tickets")
public class TicketPurchaseRestController {

    private final SeatBookingServiceFeign seatBookingServiceFeign;
    private final TicketsService ticketsService;

    @Autowired
    public TicketPurchaseRestController(SeatBookingServiceFeign seatBookingServiceFeign, TicketsService ticketsService) {
        this.seatBookingServiceFeign = seatBookingServiceFeign;
        this.ticketsService = ticketsService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<TicketResponseDto> purchaseTicket(@RequestBody TicketRequestDto ticketRequestDto) {
        TicketResponseDto ticketResponse = ticketsService.purchaseTicket(ticketRequestDto);
        return ResponseEntity.ok(ticketResponse);
    }

    @PostMapping("/validate-seats")
    public ResponseEntity<ValidationResponseDto> validateSeats(@RequestBody ValidationRequestDto validationRequestDto) {
        ValidationResponseDto validationResponse = seatBookingServiceFeign.validateSeats(validationRequestDto);
        return ResponseEntity.ok(validationResponse);
    }
}