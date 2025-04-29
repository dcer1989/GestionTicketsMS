package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReservationsNotFoundException extends ResponseStatusException {
    public ReservationsNotFoundException() {
        super(HttpStatus.NOT_FOUND, "One or more reservations were not found");
    }
}
