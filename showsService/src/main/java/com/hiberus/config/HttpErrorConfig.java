package com.hiberus.config;

import com.hiberus.exception.InvalidShowException;
import com.hiberus.exception.ShowNotFoundException;
import com.hiberus.exception.ShowtimeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpErrorConfig {

    @ExceptionHandler(ShowtimeNotFoundException.class)
    public ResponseEntity<String> handleShowtimeNotFoundException(ShowtimeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getReason());
    }

    @ExceptionHandler(ShowNotFoundException.class)
    public  ResponseEntity<String> ShowNotFoundException(ShowNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getReason());
    }

    @ExceptionHandler(InvalidShowException.class)
    public ResponseEntity<String> handleInvalidShowException(InvalidShowException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
