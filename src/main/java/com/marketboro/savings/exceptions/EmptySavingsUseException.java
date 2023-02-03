package com.marketboro.savings.exceptions;

public class EmptySavingsUseException extends RuntimeException{
    public static final String message = "취소할 수 있는 사용 적립금이 없습니다.";
    public EmptySavingsUseException(){
        super(message);
    }
}
