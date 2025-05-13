package com.hiberus.config;

import com.hiberus.exception.InvalidShowException;
import com.hiberus.exception.ShowNotFoundException;
import com.hiberus.exception.ShowtimeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class HttpErrorConfig {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(ShowtimeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleShowtimeNotFoundException(ShowtimeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(ShowNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleShowNotFoundException(ShowNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(InvalidShowException.class)
    public ResponseEntity<Map<String, String>> handleInvalidShowException(InvalidShowException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", ex.getMessage()));
    }
}