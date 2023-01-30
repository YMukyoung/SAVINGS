package com.marketboro.savings.repository;

import com.marketboro.savings.dto.SavingsSumDto;
import com.marketboro.savings.entity.savings.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;


public interface SavingsRepository extends JpaRepository<Savings, Long> {
    @Query("select sum(s.remindSavings) " +
            "from Savings s " +
            "where s.userNumber = :userNumber " +
            "and s.status = 'A' " +
            "and s.expiredDate >= current_date " +
            "group by s.userNumber")
    BigDecimal findSavingsSumByUser(@Param("userNumber") String userNumber);
}
