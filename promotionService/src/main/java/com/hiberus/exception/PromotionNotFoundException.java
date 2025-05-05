package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class PromotionNotFoundException extends ResponseStatusException {

    public PromotionNotFoundException(UUID promotionId) {
        super(HttpStatus.NOT_FOUND, "Promotion with ID " + promotionId + " not found");
    }
}
