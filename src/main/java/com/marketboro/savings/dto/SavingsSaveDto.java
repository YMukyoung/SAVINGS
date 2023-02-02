package com.marketboro.savings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class SavingsSaveDto {
    @Getter
    @NoArgsConstructor
    public static class Request{
        private BigDecimal saveSavings;
        private String remarks;
        @Builder
        public Request(BigDecimal saveSavings, String remarks){
            this.saveSavings = saveSavings;
            this.remarks = remarks;
        }
    }
    @Getter
    @Builder
    public static class Response{
        private BigDecimal savedSavings;
    }
}
