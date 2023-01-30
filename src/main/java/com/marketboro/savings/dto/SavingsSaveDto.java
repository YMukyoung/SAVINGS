package com.marketboro.savings.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

public class SavingsSaveDto {
    @Getter
    public static class Request{
        private String userNumber;
        private BigDecimal saveSavings;
    }
    @Getter
    @Builder
    public static class Response{
        private BigDecimal savedSavings;
    }
}
