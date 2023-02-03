package com.marketboro.savings.dto;

import com.marketboro.savings.entity.savings.Savings;
import com.marketboro.savings.entity.savings.SavingsUse;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SavingsHistoryDto {
    @Getter
    @Builder
    public static class SaveResponse {
        Page<SaveHistory> saveHistoryList;

        public static SaveResponse createResponse(Page<SaveHistory> saveHistoryList) {
            return SaveResponse
                    .builder()
                    .saveHistoryList(saveHistoryList)
                    .build();
        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class SaveHistory {
            private String userNumber;
            private BigDecimal savedSavings;
            private String remarks;
            private LocalDateTime regDate;

            @QueryProjection
            public SaveHistory(Savings savings) {
                this.userNumber = savings.getUserNumber();
                this.savedSavings = savings.getDefaultSavings();
                this.remarks = savings.getRemarks();
                this.regDate = savings.getRegDate();
            }

        }
    }
    @Getter
    @Builder
    public static class UseResponse {
        Page<UseHistory> useHistoryList;

        public static UseResponse createResponse(Page<UseHistory> useHistoryList) {
            return UseResponse
                    .builder()
                    .useHistoryList(useHistoryList)
                    .build();
        }
        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class UseHistory{
            private String userNumber;
            private BigDecimal useSavings;
            private String remarks;
            private String cancelYn;
            private String cancelReason;
            private LocalDateTime regDate;
            private LocalDateTime cancelDate;

            @QueryProjection
            public UseHistory(SavingsUse savingsUse){
                this.userNumber = savingsUse.getUserNumber();
                this.useSavings = savingsUse.getUseSavings();
                this.remarks = savingsUse.getRemarks();
                this.cancelYn = savingsUse.getCancelYn().getCancelMsg();
                this.cancelReason = savingsUse.getCancelReason();
                this.regDate = savingsUse.getRegDate();
                this.cancelDate = savingsUse.getCancelDate();
            }
        }
    }
}
