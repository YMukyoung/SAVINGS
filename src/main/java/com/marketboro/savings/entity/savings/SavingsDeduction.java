package com.marketboro.savings.entity.savings;

import com.marketboro.savings.entity.common.Yn;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "SAVINGS_DEDUCTION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SavingsDeduction {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "SAVINGS_IDX", columnDefinition = "Long", nullable = false)
    private Savings savings;
    @ManyToOne
    @JoinColumn(name = "SAVINGS_USE_IDX", columnDefinition = "Long", nullable = false)
    private SavingsUse savingsUse;
    @Column(name = "USER_NUMBER", nullable = false)
    private String userNumber;
    @Column(name = "DEDUCTION_SAVINGS", nullable = false)
    private BigDecimal deductionSavings;
    @Enumerated(EnumType.STRING)
    @Column(name = "CANCEL_YN", columnDefinition = "Character", nullable = false)
    private Yn cancelYn;
    @CreationTimestamp
    @Column(name = "REG_DATE", nullable = false)
    private LocalDateTime regDate;
    @Column(name = "CANCEL_DATE")
    private LocalDateTime cancelDate;

    @Builder
    public SavingsDeduction(Savings savings, SavingsUse savingsUse, String userNumber, BigDecimal deductionSavings, Yn cancelYn){
        this.savings = savings;
        this.savingsUse = savingsUse;
        this.userNumber = userNumber;
        this.deductionSavings = deductionSavings;
        this.cancelYn = cancelYn;
    }
    public static SavingsDeduction createSavingsUse(Savings savings, SavingsUse savingsUse, BigDecimal deductionSavings) {
        return SavingsDeduction
                .builder()
                .savings(savings)
                .savingsUse(savingsUse)
                .userNumber(savings.getUserNumber())
                .deductionSavings(deductionSavings)
                .cancelYn(Yn.N)
                .build();
    }
}
