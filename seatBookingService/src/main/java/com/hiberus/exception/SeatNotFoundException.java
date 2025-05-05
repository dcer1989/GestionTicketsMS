package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class SeatNotFoundException extends ResponseStatusException {
    public SeatNotFoundException(UUID seatId) {
        super(HttpStatus.NOT_FOUND, "Seat with ID " + seatId + " not found");
    }

    public SeatNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Uno o más asientos no fueron encontrados");
    }
}