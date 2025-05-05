package com.hiberus.config;

import com.hiberus.exception.PromotionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpErrorConfig {

    @ExceptionHandler(PromotionNotFoundException.class)
    public ResponseEntity<String> handlePromotionNotFoundException(PromotionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getReason());
    }
}
