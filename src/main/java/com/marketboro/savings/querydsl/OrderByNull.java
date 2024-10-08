package com.marketboro.savings.querydsl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.NullExpression;

public class OrderByNull extends OrderSpecifier{
    public static final OrderByNull DEFAULT = new OrderByNull();
    private OrderByNull() {
        super(Order.ASC, NullExpression.DEFAULT, NullHandling.Default);
    }
}
