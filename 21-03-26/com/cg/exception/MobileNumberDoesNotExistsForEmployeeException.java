package com.cg.exception;

public class MobileNumberDoesNotExistsForEmployeeException extends RuntimeException {
    public MobileNumberDoesNotExistsForEmployeeException(String message) {
        super(message);
    }
}