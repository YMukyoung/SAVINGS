package com.marketboro.savings.exceptions;

public class SavingsSaveMinusException extends RuntimeException{
    public static final String message = "적립금은 0보다 커야합니다.";

    public SavingsSaveMinusException(){
        super(message);
    }
}
