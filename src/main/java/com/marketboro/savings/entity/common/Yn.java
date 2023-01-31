package com.marketboro.savings.entity.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Yn {
    Y("취소"),
    N("정상")
    ;

    private String cancelMsg;
}
