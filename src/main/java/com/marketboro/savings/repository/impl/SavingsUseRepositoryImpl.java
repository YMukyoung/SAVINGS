package com.marketboro.savings.repository.impl;

import com.marketboro.savings.dto.QSavingsHistoryDto_UseResponse_UseHistory;
import com.marketboro.savings.dto.SavingsHistoryDto;
import com.marketboro.savings.repository.custom.SavingsUseCustomRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.marketboro.savings.entity.savings.QSavingsUse.savingsUse;

@RequiredArgsConstructor
public class SavingsUseRepositoryImpl implements SavingsUseCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<SavingsHistoryDto.UseResponse.UseHistory> findSavingsUseHistory(Pageable pageable, String userNumber) {
        List<SavingsHistoryDto.UseResponse.UseHistory> content = getHistoryContent(pageable, userNumber);
        Long count = getHistoryCount(userNumber);

        return new PageImpl<>(content, pageable, count);
    }

    private Long getHistoryCount(String userNumber) {
        return jpaQueryFactory
                .select(savingsUse.count())
                .from(savingsUse)
                .where(
                        isEqualsUserNumber(userNumber)
                )
                .fetchOne();
    }

    private List<SavingsHistoryDto.UseResponse.UseHistory> getHistoryContent(Pageable pageable, String userNumber) {
        return jpaQueryFactory
                .select(new QSavingsHistoryDto_UseResponse_UseHistory(
                        savingsUse
                ))
                .from(savingsUse)
                .where(
                        isEqualsUserNumber(userNumber)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression isEqualsUserNumber(String userNumber){
        return userNumber != null ? savingsUse.userNumber.eq(userNumber) : null;
    }
}
