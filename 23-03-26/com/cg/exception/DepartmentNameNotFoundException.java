package com.cg.exception;

public class DepartmentNameNotFoundException extends RuntimeException {
    public DepartmentNameNotFoundException(String message) {
        super(message);
    }
}