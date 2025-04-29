package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class ShowtimeNotFoundException extends ResponseStatusException {
    public ShowtimeNotFoundException(UUID showtimeId) {
        super(HttpStatus.NOT_FOUND, "Showtime with ID " + showtimeId + " not found");
    }
}