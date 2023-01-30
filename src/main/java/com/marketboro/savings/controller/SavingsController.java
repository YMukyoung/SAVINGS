package com.marketboro.savings.controller;

import com.marketboro.savings.dto.SavingsSaveDto;
import com.marketboro.savings.dto.SavingsSumDto;
import com.marketboro.savings.dto.common.ApiResponse;
import com.marketboro.savings.enums.common.SuccessCode;
import com.marketboro.savings.service.SavingsService;
import lombok.RequiredArgsConstructor;
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
        return ApiResponse
                .<SavingsSumDto.Response>builder()
                .code(SuccessCode.S)
                .message(SuccessCode.S.getMsg())
                .data(savingsService.findSavingsSum(userNumber))
                .build();
    }
    /**
     * 회원별 적립금 적립/사용 내역 조회
     * */
    @GetMapping("/{userNumber}/history")
    public String getSavingsHistory(@PathVariable String userNumber){
        return "";
    }
    /**
     * 회원별 적립금 적립
     * */
    @PostMapping("/save")
    public ApiResponse<SavingsSaveDto.Response> save(@RequestBody SavingsSaveDto.Request request){
        return ApiResponse
                .<SavingsSaveDto.Response>builder()
                .code(SuccessCode.S)
                .message(SuccessCode.S.getMsg())
                .data(savingsService.save(request))
                .build();
    }
    /**
     * 회원별 적립금 사용
     * */
    @PostMapping("/use")
    public String use(@RequestBody String userNumber){
        return "";
    }
    /**
     * 회원별 적립금 사용취소
     * */
    @PostMapping("cancel")
    public String cancel(@RequestBody String userNumber){
        return "";
    }
}
