package com.hiberus.config;

import com.hiberus.exception.InvalidReservationException;
import com.hiberus.exception.KafkaSendException;
import com.hiberus.exception.ReservationExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpErrorConfig {

    @ExceptionHandler(ReservationExpiredException.class)
    public ResponseEntity<String> handleReservationExpiredException(ReservationExpiredException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getReason());
    }
    @ExceptionHandler(InvalidReservationException.class)
    public ResponseEntity<String> handleInvalidReservationException(InvalidReservationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getReason());
    }

    @ExceptionHandler(KafkaSendException.class)
    public ResponseEntity<String> handleKafkaSendException(KafkaSendException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}