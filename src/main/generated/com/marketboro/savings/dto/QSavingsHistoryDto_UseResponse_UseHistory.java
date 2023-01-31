package com.marketboro.savings.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.marketboro.savings.dto.QSavingsHistoryDto_UseResponse_UseHistory is a Querydsl Projection type for UseHistory
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSavingsHistoryDto_UseResponse_UseHistory extends ConstructorExpression<SavingsHistoryDto.UseResponse.UseHistory> {

    private static final long serialVersionUID = 1793825884L;

    public QSavingsHistoryDto_UseResponse_UseHistory(com.querydsl.core.types.Expression<? extends com.marketboro.savings.entity.savings.SavingsUse> savingsUse) {
        super(SavingsHistoryDto.UseResponse.UseHistory.class, new Class<?>[]{com.marketboro.savings.entity.savings.SavingsUse.class}, savingsUse);
    }

}

