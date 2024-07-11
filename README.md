# spring-gift-jpa

### 1단계 기능 요구 사항
- 지금까지 작성한 JdbcTemplate 기반 코드를 JPA로 리팩터링하고 실제 도메인 모델을 어떻게 구성하고 객체와 테이블을 어떻게 매핑해야 하는지 알아본다.
  - [x] ProductRepository 생성
  - [x] MemberRepository 생성
  - [x] WishRepository 생성
  - [x] ProductDao에서 ProductRepository로 변경
    - [x] validate를 Product로 옮김.
  - [x] MemberDao에서 MemberRepository로 변경
  - [x] WishDao에서 WishRepository로 변경

@DataJpaTest를 사용하여 학습 테스트를 해 본다.

### 2단계 기능 요구 사항
객체의 참조와 테이블의 외래 키를 매핑해서 객체에서는 참조를 사용하고 테이블에서는 외래 키를 사용할 수 있도록 한다.
- [ ] Wish Entity 수정
- [ ] WishRepository 수정
- [ ] Test 코드 수정
