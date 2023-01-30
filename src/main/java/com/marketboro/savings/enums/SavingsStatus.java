package com.marketboro.savings.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SavingsStatus {
    A("이용 가능"),
    E("사용 완료")
    ;

    private String descriptions;
}
