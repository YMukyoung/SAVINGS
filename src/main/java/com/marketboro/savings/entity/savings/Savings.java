package com.marketboro.savings.entity.savings;

import com.marketboro.savings.dto.SavingsSaveDto;
import com.marketboro.savings.enums.SavingsStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "SAVINGS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Savings {
    @Id
    @Column(name = "IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(name = "USER_NUMBER", nullable = false)
    private String userNumber;
    @Column(name = "DEFAULT_SAVINGS", nullable = false)
    private BigDecimal defaultSavings;
    @Column(name = "REMIND_SAVINGS", nullable = false)
    private BigDecimal remindSavings;
    @Column(name = "STATUS", columnDefinition = "Character", nullable = false)
    @Enumerated(EnumType.STRING)
    private SavingsStatus status;
    @CreationTimestamp
    @Column(name = "REG_DATE", nullable = false)
    private LocalDateTime regDate;
    @Column(name = "EXPIRED_DATE", nullable = false)
    private LocalDate expiredDate;
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Builder
    public Savings(String userNumber, BigDecimal savings, SavingsStatus status, LocalDateTime nowDate, LocalDateTime updateDate){
        this.userNumber = userNumber;
        this.defaultSavings = savings;
        this.remindSavings = savings;
        this.status = status;
        this.expiredDate = nowDate.toLocalDate().plusYears(1L);
        this.updateDate = updateDate;
    }

    public static Savings createSavingForSave(SavingsSaveDto.Request request) {
        return Savings.builder()
                .userNumber(request.getUserNumber())
                .savings(request.getSaveSavings())
                .status(SavingsStatus.A)
                .nowDate(LocalDateTime.now())
                .build();
    }

    public SavingsSaveDto.Response toDto() {
        return SavingsSaveDto.Response
                .builder()
                .savedSavings(this.defaultSavings)
                .build();
    }

    public boolean isMinusSavings() {
        return this.defaultSavings.compareTo(BigDecimal.ZERO) < 0;
    }
}
