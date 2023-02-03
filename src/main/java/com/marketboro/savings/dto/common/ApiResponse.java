package com.marketboro.savings.dto.common;

import com.marketboro.savings.dto.SavingsSaveDto;
import com.marketboro.savings.enums.common.SuccessCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private SuccessCode code;
    private String message;
    private T data;

    @Builder
    ApiResponse(SuccessCode code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T>ApiResponse<T> createSuccessResponse(T data) {
        return ApiResponse.<T>builder()
                .code(SuccessCode.S)
                .message(SuccessCode.S.getMsg())
                .data(data)
                .build();
    }

    public static ApiResponse createErrorResponse(String message) {
        return ApiResponse.builder()
                .code(SuccessCode.F)
                .message(message)
                .build();
    }
}
