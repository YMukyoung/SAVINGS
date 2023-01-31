package com.marketboro.savings.repository;

import com.marketboro.savings.entity.savings.SavingsDeduction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsDeductionRepository extends JpaRepository<SavingsDeduction, Long> {

}
