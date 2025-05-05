package com.hiberus.controller;

import com.hiberus.dto.*;
import com.hiberus.mapper.ReservationRequestMapper;
import com.hiberus.mapper.ReservationResponseMapper;
import com.hiberus.mapper.SeatMapper;
import com.hiberus.model.ReservationStatus;
import com.hiberus.usecase.*;
import com.hiberus.mapper.UpdateResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SeatsRestController {

    private final GetAllSeatsUseCase getAllSeatsUseCase;
    private final GetSeatByIdUseCase getSeatByIdUseCase;
    private final DeactivateSeatByIdUseCase deactivateSeatByIdUseCase;
    private final ReserveSeatsUseCase reserveSeatsUseCase;
    private final UpdateReservationStatusUseCase updateReservationStatusUseCase;
    private final SeatMapper seatMapper;
    private final ReservationRequestMapper reservationRequestMapper;
    private final ReservationResponseMapper reservationResponseMapper;
    private final UpdateResponseMapper updateResponseMapper;

    @GetMapping("/v1/seats")
    @ResponseStatus(HttpStatus.OK)
    public List<SeatResponse> getAllSeats() {
    
        log.info("Request received to fetch all seats");

        return getAllSeatsUseCase.getAllSeats().stream()
                .map(seatMapper::toDto)
                .toList();
    }

    @GetMapping("/v1/seats/{seatId}")
    @ResponseStatus(HttpStatus.OK)
    public SeatResponse getSeatById(@PathVariable UUID seatId) {

        log.info("Fetching seat with ID: {}", seatId);

        return seatMapper.toDto(getSeatByIdUseCase.getSeatById(seatId));
    }

    @DeleteMapping("/v1/seats/{seatId}")
    @ResponseStatus(HttpStatus.OK)
    public void deactivateSeatById(@PathVariable UUID seatId) {

        log.info("Request received to deactivate seat with ID: {}", seatId);

        deactivateSeatByIdUseCase.deactivateSeatById(seatId);
    }

    @PostMapping("/v1/seats/reserve")
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponse reserveSeats(
            @RequestBody ReservationRequest reservationRequest) {

        log.info("Request received to reserve seats: {}", reservationRequest.seatIds());

        return reservationResponseMapper.toDto(reserveSeatsUseCase.reserveSeats(reservationRequestMapper.toEntity(reservationRequest)));
    }

    @PostMapping("/v1/reservations/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdateResponse updateReservations(
            @RequestBody UpdateRequest updateRequest) {

        log.info("Request received to update reservations: {}", updateRequest.reservationId());

        return updateResponseMapper.toDto(updateRequest.reservationId(), updateReservationStatusUseCase.updateReservationStatus(updateRequest.reservationId(), false));
    }
}