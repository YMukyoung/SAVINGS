package com.marketboro.savings.exceptions;

public class LackSavingsException extends RuntimeException{
    public static final String message = "사용 가능한 적립금이 부족합니다.";
    public LackSavingsException(){
        super(message);
    }
}
