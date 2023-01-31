package com.marketboro.savings.controller;

import com.marketboro.savings.dto.SavingsHistoryDto;
import com.marketboro.savings.dto.SavingsSaveDto;
import com.marketboro.savings.dto.SavingsSumDto;
import com.marketboro.savings.dto.SavingsUseDto;
import com.marketboro.savings.dto.common.ApiResponse;
import com.marketboro.savings.service.SavingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/savings")
public class SavingsController {
    private final SavingsService savingsService;

    /**
     * 회원별 적립금 합계 조회
     */
    @GetMapping("/{userNumber}")
    public ApiResponse<SavingsSumDto.Response> getSavingsSum(@PathVariable String userNumber){
        return ApiResponse.createSuccessResponse(savingsService.findSavingsSum(userNumber));
    }
    /**
     * 회원별 적립금 적립 내역 조회
     * */
    @GetMapping("/history/save/{userNumber}")
    public ApiResponse<SavingsHistoryDto.SaveResponse> getSavingsSaveHistory(@PathVariable String userNumber
                                                                     , Pageable pageable
                                                                     ) {
        return ApiResponse.createSuccessResponse(savingsService.findAllSaveHistory(pageable, userNumber));
    }
    /**
     * 회원별 적립금 적립 내역 조회
     * */
    @GetMapping("/history/use/{userNumber}")
    public ApiResponse<SavingsHistoryDto.UseResponse> getSavingsUseHistory(@PathVariable String userNumber
                                                                     , Pageable pageable
                                                                     ) {
        return ApiResponse.createSuccessResponse(savingsService.findAllUseHistory(pageable, userNumber));
    }
    /**
     * 회원별 적립금 적립
     * */
    @PostMapping("/save")
    public ApiResponse<SavingsSaveDto.Response> save(@RequestBody SavingsSaveDto.Request request){
        return ApiResponse.createSuccessResponse(savingsService.save(request));
    }
    /**
     * 회원별 적립금 사용
     * */
    @PostMapping("/use")
    public ApiResponse<SavingsUseDto.Response> use(@RequestBody SavingsUseDto.Request request){
        return ApiResponse.createSuccessResponse(savingsService.use(request));
    }
    /**
     * 회원별 적립금 사용취소
     * */
    @PostMapping("cancel")
    public String cancel(@RequestBody String userNumber){
        return "";
    }
}
