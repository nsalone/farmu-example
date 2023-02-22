package com.farmu.example.common.exception;

public class ProviderRetryException extends RuntimeException {

    public ProviderRetryException(final RuntimeException cause) {
        super(cause.getMessage(), cause);
    }

}