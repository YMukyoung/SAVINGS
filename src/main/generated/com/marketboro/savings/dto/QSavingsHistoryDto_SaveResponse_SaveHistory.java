package com.marketboro.savings.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.marketboro.savings.dto.QSavingsHistoryDto_SaveResponse_SaveHistory is a Querydsl Projection type for SaveHistory
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSavingsHistoryDto_SaveResponse_SaveHistory extends ConstructorExpression<SavingsHistoryDto.SaveResponse.SaveHistory> {

    private static final long serialVersionUID = 1771046224L;

    public QSavingsHistoryDto_SaveResponse_SaveHistory(com.querydsl.core.types.Expression<? extends com.marketboro.savings.entity.savings.Savings> savings) {
        super(SavingsHistoryDto.SaveResponse.SaveHistory.class, new Class<?>[]{com.marketboro.savings.entity.savings.Savings.class}, savings);
    }

}

