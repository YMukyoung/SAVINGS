package com.marketboro.savings.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/savings")
public class SavingsController {
    /**
     * 회원별 적립금 합계 조회
     */
    @GetMapping("/{userNumber}")
    public String getSavingsSum(@PathVariable String userNumber){
        return "";
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
    public String save(@RequestBody String userNumber){
        return "";
    }
    /**
     * 회원별 적립금 사용
     * */
    @PostMapping("use")
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
