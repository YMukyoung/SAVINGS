package com.marketboro.savings.entity.savings;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSavingsDeduction is a Querydsl query type for SavingsDeduction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSavingsDeduction extends EntityPathBase<SavingsDeduction> {

    private static final long serialVersionUID = -2040225796L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSavingsDeduction savingsDeduction = new QSavingsDeduction("savingsDeduction");

    public final DateTimePath<java.time.LocalDateTime> cancelDate = createDateTime("cancelDate", java.time.LocalDateTime.class);

    public final EnumPath<com.marketboro.savings.entity.common.Yn> cancelYn = createEnum("cancelYn", com.marketboro.savings.entity.common.Yn.class);

    public final NumberPath<java.math.BigDecimal> deductionSavings = createNumber("deductionSavings", java.math.BigDecimal.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final QSavings savings;

    public final QSavingsUse savingsUse;

    public final StringPath userNumber = createString("userNumber");

    public QSavingsDeduction(String variable) {
        this(SavingsDeduction.class, forVariable(variable), INITS);
    }

    public QSavingsDeduction(Path<? extends SavingsDeduction> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSavingsDeduction(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSavingsDeduction(PathMetadata metadata, PathInits inits) {
        this(SavingsDeduction.class, metadata, inits);
    }

    public QSavingsDeduction(Class<? extends SavingsDeduction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.savings = inits.isInitialized("savings") ? new QSavings(forProperty("savings")) : null;
        this.savingsUse = inits.isInitialized("savingsUse") ? new QSavingsUse(forProperty("savingsUse")) : null;
    }

}

