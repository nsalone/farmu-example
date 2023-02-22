package com.farmu.example.common.exception;

public class ProviderAuthException extends RuntimeException {

    public ProviderAuthException(final String message) {
        super(message);
    }

    public ProviderAuthException(final RuntimeException cause) {
        super(cause.getMessage(), cause);
    }

}
