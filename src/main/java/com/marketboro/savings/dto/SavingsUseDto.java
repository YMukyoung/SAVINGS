package com.marketboro.savings.dto;

import com.marketboro.savings.entity.savings.SavingsUse;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

public class SavingsUseDto {
    @Getter
    public static class Request {
        private BigDecimal useSavings;
        private String remarks;
    }
    @Getter
    @Builder
    public static class Response {
        private Long idx;
        private BigDecimal useSavings;

        public static Response from(SavingsUse savingsUse) {
            return Response.builder()
                    .idx(savingsUse.getIdx())
                    .useSavings(savingsUse.getUseSavings())
                    .build();
        }
    }
}
