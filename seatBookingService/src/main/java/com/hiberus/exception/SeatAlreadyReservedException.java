package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class SeatAlreadyReservedException extends ResponseStatusException {
    public SeatAlreadyReservedException(UUID seatId) {
        super(HttpStatus.CONFLICT, "El asiento " + seatId + " ya está reservado");
    }
}