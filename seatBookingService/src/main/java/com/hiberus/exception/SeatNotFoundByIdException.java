package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class SeatNotFoundByIdException extends ResponseStatusException {
    public SeatNotFoundByIdException(UUID seatId) {
        super(HttpStatus.NOT_FOUND, "Seat with ID " + seatId + " not found");
    }
}