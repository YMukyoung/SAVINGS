package com.marketboro.savings.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    S("정상"),
    F("실패")
    ;

    private String msg;
}
