package com.marketboro.savings.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

public class SavingsCancelDto {
    @Getter
    public static class Request {
        private String cancelReason;
    }

    @Getter
    @Builder
    public static class Response {
        private Long savingsUseIdx;
        private BigDecimal recoverSavings;

        public static Response create(Long savingsUseIdx, BigDecimal recoverSavings){
            return Response
                    .builder()
                    .savingsUseIdx(savingsUseIdx)
                    .recoverSavings(recoverSavings)
                    .build();
        }
    }
}
