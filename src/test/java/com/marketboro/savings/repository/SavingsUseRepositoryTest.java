package com.marketboro.savings.repository;

import com.marketboro.savings.entity.savings.Savings;
import com.marketboro.savings.entity.savings.SavingsUse;
import com.marketboro.savings.exceptions.EmptySavingsUseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SavingsUseRepositoryTest {
    @Autowired
    private SavingsUseRepository savingsUseRepository;

    @Test
    void 양방향_사용_테이블_조회(){
        SavingsUse savingsUse = savingsUseRepository.findById(1L)
                .orElseThrow(EmptySavingsUseException::new);

        Savings savings = savingsUse.getSavingsDeductions()
                .get(0).getSavings();

        System.out.println(savings.getDefaultSavings());
    }

}