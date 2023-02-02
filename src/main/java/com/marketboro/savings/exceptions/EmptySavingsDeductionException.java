package com.marketboro.savings.exceptions;

public class EmptySavingsDeductionException extends RuntimeException{
    public static final String message = "적립금 차감 이력이 없습니다.";
    public EmptySavingsDeductionException(){
        super(message);
    }
}
