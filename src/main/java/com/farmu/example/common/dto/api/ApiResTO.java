package com.farmu.example.common.dto.api;

import com.farmu.example.common.dto.entity.ReferenceTO;
import lombok.Getter;

import static com.farmu.example.common.dto.api.ApiResult.ERROR;
import static com.farmu.example.common.dto.api.ApiResult.OK;
import static com.farmu.example.common.utils.ValidationUtils.checkArg;

@Getter
public class ApiResTO<T> {

    private ApiResTO(final ApiResult result, final T detail) {
        this.result = result;
        this.detail = detail;
    }

    private final ApiResult result;
    private final T detail;

    public static ApiResTO<ReferenceTO> refResponse(final ReferenceTO reference) {
        checkArg(reference, "reference");
        return new ApiResTO<>(OK, reference);
    }

    public static ApiResTO<ApiErrorTO> errorResponse(final ApiErrorReason reason, final String message) {
        checkArg(reason, "reason");
        checkArg(message, "message");
        return new ApiResTO<>(ERROR, ApiErrorTO.newApiError(reason, message));
    }

}