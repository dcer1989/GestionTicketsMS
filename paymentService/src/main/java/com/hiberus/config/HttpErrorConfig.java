package com.hiberus.config;

import com.hiberus.exception.KafkaSendException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class HttpErrorConfig {

    @ExceptionHandler(KafkaSendException.class)
    public ResponseEntity<String> handleKafkaSendException(KafkaSendException ex) {
        // Aquí puedes personalizar la respuesta (por ejemplo, devolver un mensaje en formato JSON)
        return new ResponseEntity<>(
                "Error sending message to Kafka: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}

