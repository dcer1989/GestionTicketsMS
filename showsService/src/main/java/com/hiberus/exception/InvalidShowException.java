package com.hiberus.exception;

public class InvalidShowException extends RuntimeException {
    public InvalidShowException() {
        super("El show o su título no pueden ser nulos o vacíos");
    }
}