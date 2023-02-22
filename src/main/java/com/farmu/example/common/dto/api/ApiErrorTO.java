package com.farmu.example.common.dto.api;

import lombok.Getter;

import static com.farmu.example.common.utils.ValidationUtils.checkArg;

@Getter
public class ApiErrorTO {

    private ApiErrorTO(final ApiErrorReason reason, final String message) {
        this.reason = reason;
        this.message = message;
    }

    private final ApiErrorReason reason;
    private final String message;

    public static ApiErrorTO newApiError(final ApiErrorReason reason, final String message) {
        checkArg(reason, "reason");
        return new ApiErrorTO(reason, message);
    }

}