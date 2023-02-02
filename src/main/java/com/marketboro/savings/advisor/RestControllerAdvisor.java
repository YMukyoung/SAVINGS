package com.marketboro.savings.advisor;

import com.marketboro.savings.dto.common.ApiResponse;
import com.marketboro.savings.exceptions.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdvisor {
    @ExceptionHandler({SavingsSaveMinusException.class})
    public ApiResponse savingsSaveMinusException(SavingsSaveMinusException e){
        String message = e.getMessage();
        return ApiResponse.createErrorResponse(message);
    }
    @ExceptionHandler({NotAvailableSavingsException.class})
    public ApiResponse isNotAvailableSavingsException(NotAvailableSavingsException e){
        String message = e.getMessage();
        return ApiResponse.createErrorResponse(message);
    }
    @ExceptionHandler({SavingsUseMinusException.class})
    public ApiResponse savingsUseMinusException(SavingsUseMinusException e){
        String message = e.getMessage();
        return ApiResponse.createErrorResponse(message);
    }
    @ExceptionHandler({LackSavingsException.class})
    public ApiResponse lackSavingsException(LackSavingsException e){
        String message = e.getMessage();
        return ApiResponse.createErrorResponse(message);
    }
    @ExceptionHandler({EmptySavingsUseException.class})
    public ApiResponse emptySavingsUseException(EmptySavingsUseException e){
        String message = e.getMessage();
        return ApiResponse.createErrorResponse(message);
    }
    @ExceptionHandler({AlreadyCanceledSavingsUseException.class})
    public ApiResponse alreadyCanceledSavingsUseException(AlreadyCanceledSavingsUseException e){
        String message = e.getMessage();
        return ApiResponse.createErrorResponse(message);
    }
    @ExceptionHandler({ExpiredSavingsException.class})
    public ApiResponse expiredSavingsException(ExpiredSavingsException e){
        String message = e.getMessage();
        return ApiResponse.createErrorResponse(message);
    }
    @ExceptionHandler({EmptySavingsException.class})
    public ApiResponse emptySavingsException(EmptySavingsException e){
        String message = e.getMessage();
        return ApiResponse.createErrorResponse(message);
    }
    @ExceptionHandler({EmptySavingsDeductionException.class})
    public ApiResponse emptySavingsDeductionException(EmptySavingsDeductionException e){
        String message = e.getMessage();
        return ApiResponse.createErrorResponse(message);
    }
}
