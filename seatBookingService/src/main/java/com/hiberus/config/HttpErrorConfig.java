package com.hiberus.config;

import com.hiberus.exception.InactiveSeatException;
import com.hiberus.exception.SeatsNotFoundException;
import com.hiberus.exception.SeatAlreadyReservedException;
import com.hiberus.exception.SeatNotFoundByIdException;
import com.hiberus.exception.ReservationsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpErrorConfig {

    @ExceptionHandler(SeatsNotFoundException.class)
    public ResponseEntity<String> handleSeatsNotFoundException(SeatsNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getReason());
    }

    @ExceptionHandler(InactiveSeatException.class)
    public ResponseEntity<String> handleInactiveSeatException(InactiveSeatException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getReason());
    }

    @ExceptionHandler(SeatAlreadyReservedException.class)
    public ResponseEntity<String> handleSeatAlreadyReservedException(SeatAlreadyReservedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getReason());
    }

    @ExceptionHandler(SeatNotFoundByIdException.class)
    public ResponseEntity<String> handleSeatNotFoundByIdException(SeatNotFoundByIdException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getReason());
    }

    @ExceptionHandler(ReservationsNotFoundException.class)
    public ResponseEntity<String> handleReservationsNotFoundException(ReservationsNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getReason());
    }
}