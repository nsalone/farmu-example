package com.farmu.example.common.utils;


import com.farmu.example.common.exception.ProviderException;
import com.google.common.base.Preconditions;
import org.springframework.http.HttpStatus;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Util functions for validations of objects.
 */
public class ValidationUtils {

    /**
     * Check if a param has value. Throws IllegalArgumentException if not.
     */
    public static void checkArg(final Object param, final String paramName) {
        Preconditions.checkArgument(hasContent(param), String.format("Param '%s' is mandatory", paramName));
    }


    /**
     * Check if a param has value. Throws IllegalStateException if not.
     */
    public static void checkState(final Object field, final String fieldName) {
        Preconditions.checkState(hasContent(field), String.format("Field '%s' is mandatory", fieldName));
    }


    /**
     * Evaluates if object has content.
     */
    public static boolean hasContent(final Object object) {
        if (isNull(object)) {
            return FALSE;

        } else if ((object instanceof String)) {
            return isNotBlank(((String) object));

        } else {
            return TRUE;
        }
    }

    /**
     * Check if the provider error was because a specific reason.
     */
    public static void checkErrorReason(final ProviderException e, final HttpStatus httpStatus, final String reason) {
        if (!httpStatus.equals(e.getRequest().getHttpStatus()) || !reason.equals(e.getRequest().getErrorReason())) {
            throw e;
        }
    }

}