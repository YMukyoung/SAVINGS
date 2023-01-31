package com.marketboro.savings.exceptions;

public class SavingsUseMinusException extends RuntimeException{
    public static final String message = "적립금은 0원 이상 사용해야 합니다.";

    public SavingsUseMinusException(){
        super(message);
    }
}
