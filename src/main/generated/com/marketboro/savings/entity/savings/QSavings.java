package com.marketboro.savings.entity.savings;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSavings is a Querydsl query type for Savings
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSavings extends EntityPathBase<Savings> {

    private static final long serialVersionUID = 1686980201L;

    public static final QSavings savings = new QSavings("savings");

    public final NumberPath<java.math.BigDecimal> defaultSavings = createNumber("defaultSavings", java.math.BigDecimal.class);

    public final DatePath<java.time.LocalDate> expiredDate = createDate("expiredDate", java.time.LocalDate.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath remarks = createString("remarks");

    public final NumberPath<java.math.BigDecimal> remindSavings = createNumber("remindSavings", java.math.BigDecimal.class);

    public final ListPath<SavingsDeduction, QSavingsDeduction> savingsDeductions = this.<SavingsDeduction, QSavingsDeduction>createList("savingsDeductions", SavingsDeduction.class, QSavingsDeduction.class, PathInits.DIRECT2);

    public final EnumPath<com.marketboro.savings.enums.SavingsStatus> status = createEnum("status", com.marketboro.savings.enums.SavingsStatus.class);

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final StringPath userNumber = createString("userNumber");

    public QSavings(String variable) {
        super(Savings.class, forVariable(variable));
    }

    public QSavings(Path<? extends Savings> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSavings(PathMetadata metadata) {
        super(Savings.class, metadata);
    }

}

