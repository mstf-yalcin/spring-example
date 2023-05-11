package com.spring.jwt.demo.exception;

public class DuplicateFieldException extends RuntimeException {
    public DuplicateFieldException(String message) {
        super(message);
    }
}
