package com.farmu.example.common.utils;

import com.farmu.example.common.exception.ProviderException;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.function.Supplier;

import static java.util.Optional.empty;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class ProviderUtils {

    public static <T> Optional<T> safeFind(final Supplier<T> function) {
        return safeFind(function, UNPROCESSABLE_ENTITY, "NOT_FOUND");
    }

    public static <T> Optional<T> safeFind(final Supplier<T> function, final HttpStatus httpStatus, final String reason) {
        try {
            return Optional.of(function.get());
        } catch (final ProviderException e) {
            ValidationUtils.checkErrorReason(e, httpStatus, reason);
            return empty();
        }
    }

}
