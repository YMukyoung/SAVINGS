package com.marketboro.savings.exceptions;

public class SavingsMinusException extends RuntimeException{
    public static final String message = "적립금은 0보다 커야합니다.";

    public SavingsMinusException(){
        super(message);
    }
}
