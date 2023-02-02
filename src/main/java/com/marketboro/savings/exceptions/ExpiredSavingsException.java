package com.marketboro.savings.exceptions;

public class ExpiredSavingsException extends RuntimeException{
    public static final String message = "이미 만료된 적립금 입니다.";
    public ExpiredSavingsException(){
        super(message);
    }
}
