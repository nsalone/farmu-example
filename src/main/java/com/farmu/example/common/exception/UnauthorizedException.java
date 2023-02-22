package com.farmu.example.common.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(final String message) {
        super(message);
    }

    public UnauthorizedException(final Throwable cause) {
        super(cause);
    }

}