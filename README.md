# spring-gift-jpa

---
## 프로그램 요구 사항

* 자바 코드 컨벤션을 지키면서 프로그래밍한다.
    * 기본적으로 Google Java Style Guide를 원칙으로 한다.
    * 단, 들여쓰기는 '2 spaces'가 아닌 '4 spaces'로 한다.
* indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
    * 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    * 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
* 3항 연산자를 쓰지 않는다.
* 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
    * 함수(또는 메서드)가 한 가지 일만 잘 하도록 구현한다.
* else 예약어를 쓰지 않는다.
    * else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
    * 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.

## 기능 요구 사항
* 위시 리스트 코드를 옮겨 온다. 코드를 옮기는 방법에는 디렉터리의 모든 파일을 직접 복사하여 붙여 넣는 것부터 필요한 일부 파일만 이동하는 것, Git을 사용하는 것까지 여러 가지 방법이 있다. 코드 이동 시 반드시 리소스 파일, 프로퍼티 파일, 테스트 코드 등을 함께 이동한다.
* 지금까지 작성한 JdbcTemplate 기반 코드를 JPA로 리팩터링
* 상품과 위시 리스트 보기에 페이지네이션을 구현한다.