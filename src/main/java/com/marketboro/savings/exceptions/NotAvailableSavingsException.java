package com.marketboro.savings.exceptions;

public class NotAvailableSavingsException extends RuntimeException{
    private static final String message = "이용가능한 적립금이 없습니다.";

    public NotAvailableSavingsException(){
        super(message);
    }
}
