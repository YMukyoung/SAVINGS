package com.marketboro.savings.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

public class SavingsUseDto {
    @Getter
    public static class Request {
        private String userNumber;
        private BigDecimal useSavings;
        private String remarks;
    }
    @Getter
    @Builder
    public static class Response {
        private BigDecimal useSavings;

        public static Response from(BigDecimal useSavings) {
            return Response.builder()
                    .useSavings(useSavings)
                    .build();
        }
    }
}
