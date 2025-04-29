package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SeatsNotFoundException extends ResponseStatusException {
    public SeatsNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Uno o más asientos no fueron encontrados");
    }
}