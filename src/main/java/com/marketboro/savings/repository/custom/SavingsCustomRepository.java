package com.marketboro.savings.repository.custom;

import com.marketboro.savings.dto.SavingsHistoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SavingsCustomRepository {
    BigDecimal findSavingsSumByUser(String userNumber);

    Page<SavingsHistoryDto.SaveResponse.SaveHistory> findSavingsSaveHistory(Pageable pageable, String userNumber);
}
