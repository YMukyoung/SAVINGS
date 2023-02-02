package com.marketboro.savings.entity.savings;

import com.marketboro.savings.entity.common.Yn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SavingsUseTest {

    @Test
    void 취소처리(){
        SavingsUse savingsUse = SavingsUse
                .builder()
                .cancelYn(Yn.N)
                .build();

        String cancelReason = "테스트코드";
        savingsUse.cancel(cancelReason);

        Assertions.assertThat(savingsUse.getCancelYn()).isEqualTo(Yn.Y);
        Assertions.assertThat(savingsUse.getCancelReason()).isEqualTo(cancelReason);
        Assertions.assertThat(savingsUse.getCancelDate()).isNotNull();
    }
    @Test
    void 취소여부_취소O(){
        SavingsUse savingsUse = SavingsUse
                .builder()
                .cancelYn(Yn.Y)
                .build();
        Assertions.assertThat(savingsUse.isCanceled()).isTrue();
    }
    @Test
    void 취소여부_취소X(){
        SavingsUse savingsUse = SavingsUse
                .builder()
                .cancelYn(Yn.N)
                .build();
        Assertions.assertThat(savingsUse.isCanceled()).isFalse();
    }
}