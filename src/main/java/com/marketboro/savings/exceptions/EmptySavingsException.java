package com.marketboro.savings.exceptions;

public class EmptySavingsException extends RuntimeException{
    public static final String message = "복구할 적립금 내역이 없습니다.";
    public EmptySavingsException(){
        super(message);
    }
}
