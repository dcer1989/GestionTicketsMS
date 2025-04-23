package com.hiberus.controllers;

import com.hiberus.dto.*;
import com.hiberus.models.Reservation;
import com.hiberus.models.ReservationResult;
import com.hiberus.utils.ReservationResponseMapper;
import com.hiberus.utils.ReservationRequestMapper;
import com.hiberus.utils.SeatMapper;
import com.hiberus.models.Seat;
import com.hiberus.services.SeatsService;
import com.hiberus.utils.ValidationResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/seats")
public class SeatsServiceController {

    private final SeatsService seatsService;

    @Autowired
    public SeatsServiceController(SeatsService seatsService) {
        this.seatsService = seatsService;
    }

    @GetMapping
    public ResponseEntity<List<SeatResponseDto>> getSeats() {
        List<Seat> seats = seatsService.getSeats();
        List<SeatResponseDto> seatDtos = seats.stream()
                .map(SeatMapper::toDto)
                .toList();
        return ResponseEntity.ok(seatDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatResponseDto> getSeatById(@PathVariable UUID id) {
        Seat seat = seatsService.getSeatById(id);
        SeatResponseDto seatResponseDto = SeatMapper.toDto(seat); // Mapear la entidad a un DTO
        return ResponseEntity.ok(seatResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateSeatById(@PathVariable UUID id) {
        seatsService.deactivateSeatById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reserve")
    public ResponseEntity<ReservationResponseDto> reserveSeats(
            @RequestBody ReservationRequestDto reservationRequestDto) {

        Reservation reservation = ReservationRequestMapper.toEntity(reservationRequestDto);

        ReservationResult createdReservation = seatsService.reserveSeats(reservation);

        ReservationResponseDto responseDto = ReservationResponseMapper.toDto(createdReservation);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidationResponseDto> validateSeats(
            @RequestBody ValidationRequestDto validationRequestDto) {
        List<UUID> seatIds = validationRequestDto.getSeatIds();
        boolean valid = seatsService.validateSeats(seatIds);
        List<UUID> invalidSeats = seatsService.getInvalidSeats(seatIds);
        ValidationResponseDto responseDto = ValidationResponseMapper.toDto(valid, invalidSeats);
        return ResponseEntity.ok(responseDto);
    }
}