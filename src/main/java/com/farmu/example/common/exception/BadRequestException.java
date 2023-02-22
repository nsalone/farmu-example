package com.farmu.example.common.exception;

import com.farmu.example.common.dto.enums.ErrorReason;

public class BadRequestException extends RuntimeException {

    private final ErrorReason reason;

    public BadRequestException(final String message) {
        super(message);
        reason = null;
    }

    public BadRequestException(final ErrorReason reason, final String message) {
        super(message);
        this.reason = reason;
    }

}
