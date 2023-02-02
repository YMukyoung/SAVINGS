package com.marketboro.savings.entity.savings;

import com.marketboro.savings.enums.SavingsStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SavingsTest {

    @Test
    void 적립금_회복_처리(){
        Savings savings = Savings.builder()
                .status(SavingsStatus.E)
                .savings(BigDecimal.valueOf(10000L))
                .nowDate(LocalDateTime.now())
                .build();

        BigDecimal recoverSavings = BigDecimal.valueOf(2000L);
        savings.recovery(recoverSavings);

        Assertions.assertThat(savings.getRemindSavings()).isEqualTo(BigDecimal.valueOf(12000L));
        Assertions.assertThat(savings.getStatus()).isEqualTo(SavingsStatus.A);
    }

    @Test
    void 적립금_만료_여부(){
        Savings savings = Savings.builder()
                .status(SavingsStatus.E)
                .savings(BigDecimal.valueOf(10000L))
                .nowDate(LocalDateTime.now().minusYears(2L))
                .build();

        Assertions.assertThat(savings.isExpired()).isTrue();
    }

    @Test
    void 적립금_사용_완료(){
        Savings savings = Savings.builder()
                .status(SavingsStatus.A)
                .savings(BigDecimal.valueOf(10000L))
                .nowDate(LocalDateTime.now())
                .build();

        savings.end();

        Assertions.assertThat(savings.getStatus()).isEqualTo(SavingsStatus.E);
        Assertions.assertThat(savings.getRemindSavings()).isEqualTo(BigDecimal.ZERO);
        Assertions.assertThat(savings.getUpdateDate()).isNotNull();

    }

    @Test
    void 마이너스_금액_적립_여부(){
        Savings savings = Savings.builder()
                .status(SavingsStatus.A)
                .savings(BigDecimal.valueOf(-10000L))
                .nowDate(LocalDateTime.now())
                .build();

        Assertions.assertThat(savings.isMinusSavings()).isTrue();
    }
    @Test
    void 플러스_금액_적립_여부(){
        Savings savings = Savings.builder()
                .status(SavingsStatus.A)
                .savings(BigDecimal.valueOf(10000L))
                .nowDate(LocalDateTime.now())
                .build();

        Assertions.assertThat(savings.isMinusSavings()).isFalse();
    }
    @Test
    void 빵원_적립_여부(){
        Savings savings = Savings.builder()
                .status(SavingsStatus.A)
                .savings(BigDecimal.valueOf(0L))
                .nowDate(LocalDateTime.now())
                .build();

        Assertions.assertThat(savings.isMinusSavings()).isTrue();
    }
}