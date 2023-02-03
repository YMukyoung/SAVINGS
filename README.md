# SAVINGS
마켓보로 적립금 과제

### 사용방법
1. H2 DB 설치 (
- https://www.h2database.com/html/main.html 
- url : jdbc:h2:tcp://localhost/~/test
- 사용자명 : sa
- --
2. API 정보
- 적립 
  - POST /savings/save/{userNumber})
  - body saveSavings(적립할 적립금)
- 사용
  - POST /savings/use/{userNumber})
  - body useSavings(사용할 적립금)
- 적립금 합계
  - GET /savings/{userNumber}
- 적립 내역
  - GET /savings/history/save/{userNumber}?size=10&page=0
- 사용 내역
  - GET /savings/history/use/{userNumber}?size=10&page=0
- 적립 취소
  - PATCH /savings/cancel/{savingsUseIdx}
  - body cancelReason(취소 사유)
-  --
3. 테이블 구조
- 적립 테이블
  - IDX
  - USER_NUMBER (고객 번호)
  - DEFAULT_SAVINGS (최초 적립금)
  - REMAIN_SAVINGS (남은 적립금)
  - STATUS (A : 사용가능, E : 사용 완료)
  - REMARKS (비고)
  - REG_DATE (적립일자)
  - UPDATE_DATE (수정 일자)
  - EXPIRED_DATE (만료일자)
  - 개선 사항 > STATUS에 만료 상태를 추가 후 배치로 만료 처리 
- 차감 테이블
  - IDX
  - USER_NUMBER (고객 번호)
  - SAVINGS_IDX (적립 테이블 IDX)
  - SAVINGS_USE_IDX (사용 테이블 IDX)
  - DEDUCTION_SAVINGS (차감 적립금)
  - CANCEL_YN (취소 여부)
  - CANCEL_DATE (취소 일자)
  - REG_DATE (차감 일자)
- 사용 테이블
  - IDX 
  - USER_NUMBER (고객 번호)
  - USE_SAVINGS (사용 포인트)
  - REMARKS (비고)
  - CANCEL_YN (취소 여부)
  - CANCEL_REASON(취소 사유)
  - CANCEL_DATE (취소 일자)
  - REG_DATE (사용 일자)
  - 개선 사항 > 취소 사유를 코드로 관리할 수 있다면 CANCEL_CODE를 추가 후 코드로 관리
- --
4. 사용 스택
- SpringBoot
- H2 DATABASE
- JPA
- QUERY_DSL