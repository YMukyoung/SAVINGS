package com.marketboro.savings.config;

import com.marketboro.savings.dto.common.ApiResponse;
import com.marketboro.savings.enums.common.SuccessCode;
import com.marketboro.savings.exceptions.SavingsMinusException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdvisor {
    @ExceptionHandler(value = {SavingsMinusException.class})
    public ApiResponse SavingsMinusExceptions(SavingsMinusException e){
        String message = e.getMessage();

        return ApiResponse
                .builder()
                .code(SuccessCode.F)
                .message(message)
                .build();
    }
}
