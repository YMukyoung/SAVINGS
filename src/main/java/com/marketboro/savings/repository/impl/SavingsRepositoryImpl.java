package com.marketboro.savings.repository.impl;

import com.marketboro.savings.config.OrderByNull;
import com.marketboro.savings.dto.QSavingsHistoryDto_SaveResponse_SaveHistory;
import com.marketboro.savings.dto.SavingsHistoryDto;
import com.marketboro.savings.enums.SavingsStatus;
import com.marketboro.savings.repository.custom.SavingsCustomRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.marketboro.savings.entity.savings.QSavings.savings;

@RequiredArgsConstructor
public class SavingsRepositoryImpl implements SavingsCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public BigDecimal findSavingsSumByUser(String userNumber) {
        return jpaQueryFactory
                .select(savings.remindSavings.sum())
                .from(savings)
                .where(isEqualsUserNumber(userNumber)
                        , isStatusA()
                        , isExpired()
                )
                .groupBy(savings.userNumber)
                .orderBy(OrderByNull.DEFAULT)
                .fetchOne();
    }

    @Override
    public Page<SavingsHistoryDto.SaveResponse.SaveHistory> findSavingsSaveHistory(Pageable pageable, String userNumber) {
        List<SavingsHistoryDto.SaveResponse.SaveHistory> content = getHistoryContent(pageable, userNumber);
        Long count = getHistoryCount(userNumber);

        return new PageImpl<>(content, pageable, count);
    }

    private Long getHistoryCount(String userNumber) {
        return jpaQueryFactory
                .select(savings.count())
                .from(savings)
                .where(
                        isEqualsUserNumber(userNumber)
                )
                .fetchOne();
    }

    private List<SavingsHistoryDto.SaveResponse.SaveHistory> getHistoryContent(Pageable pageable, String userNumber) {
        return jpaQueryFactory
                .select(new QSavingsHistoryDto_SaveResponse_SaveHistory(
                        savings
                ))
                .from(savings)
                .where(
                        isEqualsUserNumber(userNumber)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression isExpired() {
        return savings.expiredDate.goe(LocalDate.now());
    }

    private BooleanExpression isStatusA() {
        return savings.status.eq(SavingsStatus.A);
    }

    private BooleanExpression isEqualsUserNumber(String userNumber){
        return userNumber != null ? savings.userNumber.eq(userNumber) : null;
    }
}
