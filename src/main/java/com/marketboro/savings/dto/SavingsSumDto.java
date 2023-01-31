package com.marketboro.savings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

public class SavingsSumDto {
    @Getter
    @Builder
    public static class Response {
        private BigDecimal totalSavings;

        public static Response createResponse(BigDecimal totalSavings){
            return Response
                    .builder()
                    .totalSavings(totalSavings)
                    .build();
        }
    }
}
