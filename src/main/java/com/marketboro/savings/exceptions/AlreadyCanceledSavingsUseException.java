package com.marketboro.savings.exceptions;

public class AlreadyCanceledSavingsUseException extends RuntimeException{
    public static final String message = "이미 취소된 건 입니다.";
    public AlreadyCanceledSavingsUseException(){
        super(message);
    }
}
