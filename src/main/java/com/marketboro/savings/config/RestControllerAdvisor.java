package com.marketboro.savings.config;

import com.marketboro.savings.dto.common.ApiResponse;
import com.marketboro.savings.exceptions.NotAvailableSavingsException;
import com.marketboro.savings.exceptions.LackSavingsException;
import com.marketboro.savings.exceptions.SavingsSaveMinusException;
import com.marketboro.savings.exceptions.SavingsUseMinusException;
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
}
