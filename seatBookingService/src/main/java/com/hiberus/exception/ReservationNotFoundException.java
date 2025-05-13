package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class ReservationNotFoundException extends ResponseStatusException {
    public ReservationNotFoundException(UUID reservationId) {
        super(HttpStatus.NOT_FOUND, "Seat with ID " + reservationId + " not found");
    }
    public ReservationNotFoundException() {
        super(HttpStatus.NOT_FOUND, "One or more reservations were not found");
    }
}
