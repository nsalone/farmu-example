package com.farmu.example.common.exception;

import com.farmu.example.common.exception.dto.ExceptionRequestTO;
import lombok.Getter;

/**
 * Exception used to throw when provider response status is not 200.
 */
@Getter
public class ProviderException extends RuntimeException {

    private final ExceptionRequestTO request;

    public ProviderException(final ExceptionRequestTO request) {
        super(request.fullToString());
        this.request = request;
    }

//    public ProviderException(final ProviderType provider, final Response response, final String errorReason, final String message, final String rawBody) {
//        this(ExceptionRequestTO.builder()
//                .httpMethod(response.request().httpMethod().name())
//                .httpStatus(HttpStatus.valueOf(response.status()))
//                .url(response.request().url())
//                .errorReason(errorReason)
//                .provider(provider)
//                .message(message)
//                .responseBody(rawBody)
//                .build()
//        );
//    }

    /**
     * Obtain full description of request.
     */
    public String fullMessage() {
        return request.fullToString();
    }

    /**
     * Obtain some description of request.
     */
    public String hiddenMessage() {
        return request.hiddenToString();
    }

}