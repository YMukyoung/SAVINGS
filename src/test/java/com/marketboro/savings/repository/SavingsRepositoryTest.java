package com.marketboro.savings.repository;

import com.marketboro.savings.dto.SavingsSumDto;
import com.marketboro.savings.entity.savings.Savings;
import com.marketboro.savings.enums.SavingsStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class SavingsRepositoryTest {
    @Autowired
    private SavingsRepository savingsRepository;

    @Test
    void save(){
        Savings savings = Savings.builder()
                .userNumber("test")
                .savings(BigDecimal.valueOf(10000L))
                .status(SavingsStatus.A)
                .nowDate(LocalDateTime.now())
                .build();

        Savings insertSavings = savingsRepository.save(savings);

        Assertions.assertThat(savings.getUserNumber()).isEqualTo(insertSavings.getUserNumber());
        Assertions.assertThat(savings.getDefaultSavings()).isEqualTo(insertSavings.getDefaultSavings());
        Assertions.assertThat(savings.getRemindSavings()).isEqualTo(insertSavings.getRemindSavings());
    }
}