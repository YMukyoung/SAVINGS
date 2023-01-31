package com.marketboro.savings.repository;

import com.marketboro.savings.entity.savings.SavingsUse;
import com.marketboro.savings.repository.custom.SavingsUseCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsUseRepository extends JpaRepository<SavingsUse, Long>, SavingsUseCustomRepository {
}
