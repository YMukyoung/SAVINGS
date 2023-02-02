package com.marketboro.savings.service;

import com.marketboro.savings.dto.*;
import com.marketboro.savings.dto.common.ApiResponse;
import com.marketboro.savings.entity.savings.Savings;
import com.marketboro.savings.entity.savings.SavingsDeduction;
import com.marketboro.savings.entity.savings.SavingsUse;
import com.marketboro.savings.exceptions.*;
import com.marketboro.savings.repository.SavingsDeductionRepository;
import com.marketboro.savings.repository.SavingsRepository;
import com.marketboro.savings.repository.SavingsUseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class SavingsService {
    private final SavingsRepository savingsRepository;
    private final SavingsUseRepository savingsUseRepository;
    private final SavingsDeductionRepository savingsDeductionRepository;

    public SavingsUseDto.Response use(String userNumber, SavingsUseDto.Request request) {
        BigDecimal useSavings = request.getUseSavings();
        if(useSavings.compareTo(BigDecimal.ZERO) <= 0){
            throw new SavingsUseMinusException();
        }
        BigDecimal availableTotalSavings = getAvailableTotalSavings(userNumber);
        if(useSavings.compareTo(availableTotalSavings) > 0){
            throw new LackSavingsException();
        }
        //사용 가능한 적립금 조회
        List<Savings> savings = getAvailableSavings(userNumber);
        //적립금 사용 이력 적재
        SavingsUse savingsUse = saveSavingsUse(userNumber, request);
        //적립금에서 적립금 차감
        deductionsSavings(savings, savingsUse, useSavings);

        return SavingsUseDto.Response.from(savingsUse);
    }
    public SavingsUse saveSavingsUse(String userNumber, SavingsUseDto.Request request) {
        SavingsUse savingsUse = SavingsUse.createSavingsUse(userNumber, request);
        return savingsUseRepository.save(savingsUse);
    }
    public void deductionsSavings(List<Savings> savingsList, SavingsUse savingsUse, BigDecimal useSavings) {
        List<SavingsDeduction> savingsDeductionsForSave = new ArrayList<>();

        for (Savings savings : savingsList) {
            // 적립금 < 사용금액
            if(savings.isLessThanUseSavings(useSavings)){
                BigDecimal remindSavings = savings.getRemindSavings();
                useSavings = useSavings.subtract(remindSavings);
                savings.end();
                savingsDeductionsForSave.add(SavingsDeduction.createSavingsUse(savings, savingsUse, remindSavings));
                continue;
            }
            // 적립금 == 사용금액
            if(savings.isEqualsUseSavings(useSavings)){
                savings.end();
                savingsDeductionsForSave.add(SavingsDeduction.createSavingsUse(savings, savingsUse, useSavings));
                break;
            }
            //적립금 > 사용금액
            savings.deduct(useSavings);
            savingsDeductionsForSave.add(SavingsDeduction.createSavingsUse(savings, savingsUse, useSavings));
            break;
        }

        savingsDeductionRepository.saveAll(savingsDeductionsForSave);
    }
    public SavingsSaveDto.Response save(String userNumber, SavingsSaveDto.Request request) {
        Savings savings = Savings.createSavingForSave(userNumber, request);
        if(savings.isMinusSavings()){
            throw new SavingsSaveMinusException();
        }
        return savingsRepository.save(savings).toDto();
    }
    @Transactional
    public SavingsCancelDto.Response cancel(Long savingsUseIdx, SavingsCancelDto.Request request) {
        //취소할 사용 이력 조회
        SavingsUse savingsUse = savingsUseRepository.findByIdx(savingsUseIdx)
                .orElseThrow(EmptySavingsUseException::new);

        //이미 취소된 건에 대한 예외 처리
        if(savingsUse.isCanceled()){
            throw new AlreadyCanceledSavingsUseException();
        }

        //사용 이력 취소 처리
        savingsUse.cancel(request.getCancelReason());

        //차감 이력 조회
        Map<Savings, BigDecimal> savingsInfo = new HashMap<>();
        List<SavingsDeduction> savingsDeductions = getSavingsDeductionsBySavingsUse(savingsUse);
        //차감 이력 취소 처리
        cancelSavingsDeductions(savingsDeductions, savingsInfo);

        //적립 내역 조회
        Set<Savings> savingsSet = savingsInfo.keySet();
        //차감 적립금 복구 처리
        BigDecimal totalRecoverSavings = savingsSet.stream()
                .filter(savings -> !savings.isExpired())
                .map(savings -> savings.recovery(savingsInfo.get(savings)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return SavingsCancelDto.Response.create(savingsUseIdx, totalRecoverSavings);
    }

    @Transactional(readOnly = true)
    public List<SavingsDeduction> getSavingsDeductionsBySavingsUse(SavingsUse savingsUse) {
        List<SavingsDeduction> savingsDeductions = savingsDeductionRepository.findAllBySavingsUse(savingsUse);
        if(savingsDeductions == null){
            throw new EmptySavingsDeductionException();
        }
        return savingsDeductions;
    }

    public void cancelSavingsDeductions(List<SavingsDeduction> savingsDeductions, Map<Savings, BigDecimal> savingsInfo){
        List<Long> savingsDeductionsIndex = new ArrayList<>();
        for (SavingsDeduction savingsDeduction : savingsDeductions) {
            savingsDeductionsIndex.add(savingsDeduction.getIdx());

            Savings savings = savingsDeduction.getSavings();
            BigDecimal currentDeductionSavings =  savingsDeduction.getDeductionSavings();
            BigDecimal totalDeductionSavings = savingsInfo.getOrDefault(savings, BigDecimal.ZERO);
            totalDeductionSavings = totalDeductionSavings.add(currentDeductionSavings);
            savingsInfo.put(savingsDeduction.getSavings(), totalDeductionSavings);
        }

        savingsDeductionRepository.cancelSavingsDeduction(savingsDeductionsIndex);
    }
    @Transactional(readOnly = true)
    public SavingsHistoryDto.SaveResponse findAllSaveHistory(Pageable pageable, String userNumber) {
        Page<SavingsHistoryDto.SaveResponse.SaveHistory> saveHistoryList = savingsRepository.findSavingsSaveHistory(pageable, userNumber);
        return SavingsHistoryDto.SaveResponse.createResponse(saveHistoryList);
    }
    @Transactional(readOnly = true)
    public SavingsHistoryDto.UseResponse findAllUseHistory(Pageable pageable, String userNumber) {
        Page<SavingsHistoryDto.UseResponse.UseHistory> useHistoryList = savingsUseRepository.findSavingsUseHistory(pageable, userNumber);
        return SavingsHistoryDto.UseResponse.createResponse(useHistoryList);
    }

    @Transactional(readOnly = true)
    public SavingsSumDto.Response findSavingsSum(String userNumber){
        BigDecimal totalSavings = getAvailableTotalSavings(userNumber);
        return SavingsSumDto.Response.createResponse(totalSavings);
    }
    @Transactional(readOnly = true)
    public BigDecimal getAvailableTotalSavings(String userNumber){
        BigDecimal sumSavings = savingsRepository.findSavingsSumByUser(userNumber);
        return sumSavings != null ? sumSavings : BigDecimal.ZERO;
    }
    @Transactional(readOnly = true)
    public List<Savings> getAvailableSavings(String userNumber) {
        List<Savings> availableSavings = savingsRepository.findAllSavingsByUser(userNumber);
        if(availableSavings == null){
            throw new NotAvailableSavingsException();
        }
        return availableSavings;
    }
}
