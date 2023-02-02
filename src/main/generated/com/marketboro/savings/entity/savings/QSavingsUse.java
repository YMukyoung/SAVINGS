package com.marketboro.savings.entity.savings;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSavingsUse is a Querydsl query type for SavingsUse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSavingsUse extends EntityPathBase<SavingsUse> {

    private static final long serialVersionUID = 1414922846L;

    public static final QSavingsUse savingsUse = new QSavingsUse("savingsUse");

    public final DateTimePath<java.time.LocalDateTime> cancelDate = createDateTime("cancelDate", java.time.LocalDateTime.class);

    public final StringPath cancelReason = createString("cancelReason");

    public final EnumPath<com.marketboro.savings.entity.common.Yn> cancelYn = createEnum("cancelYn", com.marketboro.savings.entity.common.Yn.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath remarks = createString("remarks");

    public final ListPath<SavingsDeduction, QSavingsDeduction> savingsDeductions = this.<SavingsDeduction, QSavingsDeduction>createList("savingsDeductions", SavingsDeduction.class, QSavingsDeduction.class, PathInits.DIRECT2);

    public final StringPath userNumber = createString("userNumber");

    public final NumberPath<java.math.BigDecimal> useSavings = createNumber("useSavings", java.math.BigDecimal.class);

    public QSavingsUse(String variable) {
        super(SavingsUse.class, forVariable(variable));
    }

    public QSavingsUse(Path<? extends SavingsUse> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSavingsUse(PathMetadata metadata) {
        super(SavingsUse.class, metadata);
    }

}

