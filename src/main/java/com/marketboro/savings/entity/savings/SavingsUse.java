package com.marketboro.savings.entity.savings;

import com.marketboro.savings.dto.SavingsUseDto;
import com.marketboro.savings.entity.common.Yn;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "SAVINGS_USE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SavingsUse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(name = "USER_NUMBER", nullable = false)
    private String userNumber;
    @Column(name = "USE_SAVINGS", nullable = false)
    private BigDecimal useSavings;
    @Column(name = "REMARKS")
    private String remarks;
    @Enumerated(EnumType.STRING)
    @Column(name = "CANCEL_YN", columnDefinition = "Character", nullable = false)
    private Yn cancelYn;
    @Column(name = "CANCEL_REASON")
    private String cancelReason;
    @CreationTimestamp
    @Column(name = "REG_DATE", nullable = false)
    private LocalDateTime regDate;
    @Column(name = "CANCEL_DATE")
    private LocalDateTime cancelDate;

    @Builder
    public SavingsUse(String userNumber, BigDecimal useSavings, String remarks, Yn cancelYn, String cancelReason, LocalDateTime cancelDate){
        this.userNumber = userNumber;
        this.useSavings = useSavings;
        this.remarks = remarks;
        this.cancelYn = cancelYn;
        this.cancelReason = cancelReason;
        this.cancelDate = cancelDate;
    }

    public static SavingsUse createSavingsUse(SavingsUseDto.Request request) {
        return SavingsUse
                .builder()
                .userNumber(request.getUserNumber())
                .useSavings(request.getUseSavings())
                .remarks(request.getRemarks())
                .cancelYn(Yn.N)
                .build();
    }
}
