package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class ReservationExpiredException extends ResponseStatusException {
    public ReservationExpiredException(UUID reservationId) {
        super(HttpStatus.CONFLICT, "La reserva con ID " + reservationId + " ha expirado. No se puede completar la compra.");
    }
}