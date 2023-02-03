package com.marketboro.savings.repository;

import com.marketboro.savings.entity.savings.SavingsDeduction;
import com.marketboro.savings.entity.savings.SavingsUse;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SavingsDeductionRepository extends JpaRepository<SavingsDeduction, Long> {
    @Modifying
    @Query("update SavingsDeduction s set s.cancelYn = com.marketboro.savings.entity.common.Yn.Y where s.idx in :ids")
    void cancelSavingsDeduction(@Param("ids") List<Long> ids);

    @EntityGraph(attributePaths = {"savings"}, type = EntityGraph.EntityGraphType.LOAD)
    List<SavingsDeduction> findAllBySavingsUse(SavingsUse savingsUse);
}
