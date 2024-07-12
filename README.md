# spring-gift-jpa
# 엔티티 매핑

## 요구사항
### 기능 요구사항
지금까지 작성한 JdbcTemplate 기반 코드를 JPA로 리팩터링하고 실제 도메인 모델을 어떻게 구성하고 객체와 테이블을 어떻게 매핑해야 하는지 알아본다.

1. 엔티티 클래스와 리포지토리 클래스를 작성해 본다.
2. @DataJpaTest를 사용하여 학습 테스트를 해 본다.

### 프로그래밍 요구 사항
1. Authorization 헤더가 유효하지 않거나 토큰이 유효하지 않은 경우 401 Unauthorized를 반환한다.
2. 잘못된 로그인, 비밀번호 찾기, 비밀번호 변경 요청은 403 Forbidden을 반환한다.

# 3단계 위시 리스트

## 요구사항
### 기능 요구사항
이전 단계에서 로그인 후 받은 토큰을 사용하여 사용자별 위시 리스트 기능을 구현한다.
1. 위시 리스트에 등록된 상품 목록을 조회할 수 있다.
2. 위시 리스트에 상품을 추가할 수 있다.
3. 위시 리스트에 담긴 상품을 삭제할 수 있다.
