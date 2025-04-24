package com.hiberus.controllers;

import com.hiberus.dto.TicketRequestDto;
import com.hiberus.dto.TicketResponseDto;
import com.hiberus.dto.ValidationRequestDto;
import com.hiberus.dto.ValidationResponseDto;
import com.hiberus.services.SeatBookingServiceFeign;
import com.hiberus.services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/tickets")
public class TicketPurchaseServiceController {

    private final SeatBookingServiceFeign seatBookingServiceFeign;
    private final TicketsService ticketsService;

    @Autowired
    public TicketPurchaseServiceController(SeatBookingServiceFeign seatBookingServiceFeign, TicketsService ticketsService) {
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