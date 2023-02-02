package com.marketboro.savings.repository;

import com.marketboro.savings.entity.savings.SavingsUse;
import com.marketboro.savings.repository.custom.SavingsUseCustomRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SavingsUseRepository extends JpaRepository<SavingsUse, Long>, SavingsUseCustomRepository {
    @EntityGraph(attributePaths = {"savingsDeductions"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<SavingsUse> findByIdx(Long idx);
}
