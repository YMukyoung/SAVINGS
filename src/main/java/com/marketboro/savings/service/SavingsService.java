package com.marketboro.savings.service;

import com.marketboro.savings.dto.SavingsSaveDto;
import com.marketboro.savings.dto.SavingsSumDto;
import com.marketboro.savings.entity.savings.Savings;
import com.marketboro.savings.exceptions.SavingsMinusException;
import com.marketboro.savings.repository.SavingsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SavingsService {
    private final SavingsRepository savingsRepository;

    @Transactional()
    public SavingsSaveDto.Response save(SavingsSaveDto.Request request) {
        Savings savings = Savings.createSavingForSave(request);

        if(savings.isMinusSavings()){
            throw new SavingsMinusException();
        }

        Savings insertedSavings = savingsRepository.save(savings);

        return insertedSavings.toDto();
    }

    public SavingsSumDto.Response findSavingsSum(String userNumber){
        BigDecimal totalSavings = savingsRepository.findSavingsSumByUser(userNumber);
         return SavingsSumDto.Response
                 .builder()
                 .totalSavings(totalSavings)
                 .build();
    }
}
