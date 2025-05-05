package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidReservationException extends ResponseStatusException {
    public InvalidReservationException() {
        super(HttpStatus.BAD_REQUEST, "The reservation cannot be null or empty");
    }
}
