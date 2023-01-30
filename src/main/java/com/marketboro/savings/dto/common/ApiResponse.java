package com.marketboro.savings.dto.common;

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
}
