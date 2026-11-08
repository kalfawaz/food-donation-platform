package com.fooddonation.exception;

// Thrown when the user sends data that breaks a business rule
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
