package com.hiberus.config;

import com.hiberus.exception.InactiveSeatException;
import com.hiberus.exception.SeatAlreadyReservedException;
import com.hiberus.exception.SeatNotFoundException;
import com.hiberus.exception.ReservationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpErrorConfig {

    @ExceptionHandler(InactiveSeatException.class)
    public ResponseEntity<String> handleInactiveSeatException(InactiveSeatException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getReason());
    }

    @ExceptionHandler(SeatAlreadyReservedException.class)
    public ResponseEntity<String> handleSeatAlreadyReservedException(SeatAlreadyReservedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getReason());
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<String> handleSeatNotFoundException(SeatNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getReason());
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<String> handleReservationsNotFoundException(ReservationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getReason());
    }
}