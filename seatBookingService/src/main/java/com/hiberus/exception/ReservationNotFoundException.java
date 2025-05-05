package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReservationNotFoundException extends ResponseStatusException {
    public ReservationNotFoundException() {
        super(HttpStatus.NOT_FOUND, "One or more reservations were not found");
    }
}
