package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class ShowNotFoundException extends ResponseStatusException {
    public ShowNotFoundException(UUID showId) {
        super(HttpStatus.NOT_FOUND, "Show with ID " + showId + " not found");
    }
}
