package com.tp.bookmydoctor.exception;

public class DataFoundException extends RuntimeException {
    private String message;
    public DataFoundException(String message) {
        super(message);
    }
}
