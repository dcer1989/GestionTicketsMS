package com.hiberus.controller;

import com.hiberus.dto.*;
import com.hiberus.mapper.ReservationRequestMapper;
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

    @GetMapping("/v1/seats")
    @ResponseStatus(HttpStatus.OK)
    public List<SeatResponse> getAllSeats() {

        log.info("Request received to fetch all seats");

        return getAllSeatsUseCase.getAllSeats().stream()
                .map(SeatMapper::toDto)
                .toList();
    }

    @GetMapping("/v1/seats/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SeatResponse getSeatById(@PathVariable UUID seatId) {

        log.info("Fetching seat with ID: {}", seatId);

        return SeatMapper.toDto(getSeatByIdUseCase.getSeatById(seatId));
    }

    @DeleteMapping("/v1/seats/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deactivateSeatById(@PathVariable UUID seatId) {

        log.info("Request received to deactivate seat with ID: {}", seatId);

        deactivateSeatByIdUseCase.deactivateSeatById(seatId);
    }

    @PostMapping("/v1/seats/reserve")
    @ResponseStatus(HttpStatus.CREATED)
    public void reserveSeats(
            @RequestBody ReservationRequest reservationRequest) {

        log.info("Request received to reserve seats: {}", reservationRequest.getSeatIds());

        reserveSeatsUseCase.reserveSeats(ReservationRequestMapper.toEntity(reservationRequest));
    }

    @PostMapping("/v1/reservations/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdateResponse updateReservations(
            @RequestBody UpdateRequest updateRequest) {

        log.info("Request received to update reservations: {}", updateRequest.getReservationIds());

        List<ReservationStatus> updatedReservationStatus = updateReservationStatusUseCase.updateReservationStatus(updateRequest.getReservationIds(), false);

        return UpdateResponseMapper.toDto(updateRequest.getReservationIds(), updatedReservationStatus);
    }
}