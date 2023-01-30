package com.marketboro.savings.entity.savings;

import com.marketboro.savings.entity.common.Yn;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "SAVINGS_USE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SavingsUse {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "SAVINGS_IDX", columnDefinition = "Long", nullable = false)
    private Savings savings;
    @Column(name = "USER_NUMBER", nullable = false)
    private String userNumber;
    @Column(name = "USE_SAVINGS", nullable = false)
    private BigDecimal remindSavings;
    @Column(name = "CANCEL_YN", columnDefinition = "Character")
    @Enumerated(EnumType.STRING)
    private Yn cancelYn;
    @CreationTimestamp
    @Column(name = "REG_DATE", nullable = false)
    private LocalDateTime regDate;
    @Column(name = "CANCEL_DATE", nullable = false)
    private LocalDateTime cancelDate;
}
