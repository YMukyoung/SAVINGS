package com.marketboro.savings.repository.custom;

import com.marketboro.savings.dto.SavingsHistoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsUseCustomRepository {
    Page<SavingsHistoryDto.UseResponse.UseHistory> findSavingsUseHistory(Pageable pageable, String userNumber);
}
