package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class InactiveSeatException extends ResponseStatusException {
    public InactiveSeatException(UUID seatId) {
        super(HttpStatus.CONFLICT, "El asiento " + seatId + " está inactivo y no puede ser reservado");
    }
}
