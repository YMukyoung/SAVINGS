package com.marketboro.savings.repository;

import com.marketboro.savings.entity.savings.Savings;
import com.marketboro.savings.entity.savings.SavingsDeduction;
import com.marketboro.savings.repository.custom.SavingsCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


public interface SavingsRepository extends JpaRepository<Savings, Long>, SavingsCustomRepository {
    @Query("select s " +
            "from Savings s " +
            "where s.userNumber = :userNumber " +
            "and s.status = 'A' " +
            "and s.expiredDate >= current_date " +
            "order by s.regDate ")
    List<Savings> findAllSavingsByUser(@Param("userNumber") String userNumber);
}
