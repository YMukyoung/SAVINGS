package com.marketboro.savings.dto;

import com.marketboro.savings.entity.savings.SavingsUse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class SavingsUseDto {
    @Getter
    @NoArgsConstructor
    public static class Request {
        private BigDecimal useSavings;
        private String remarks;

        @Builder
        public Request(BigDecimal useSavings, String remarks){
            this.useSavings = useSavings;
            this.remarks = remarks;
        }
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
