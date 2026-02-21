# 📚 Nunubooks

Spring Boot 기반 도서 쇼핑 웹 애플리케이션입니다.  
도서 검색부터 장바구니, 주문/결제까지의 전체 흐름을 직접 구현하며  
외부 API 연동, 보안 처리, AWS 배포 경험을 목표로 개발하였습니다.

---

## 🔗 배포 주소

http://nunubooks.com

---

## 🛠 기술 스택

### Backend
- Java 17
- Spring Boot
- MyBatis
- REST API 일부 구현 (AJAX 기반 비동기 처리)
- SHA-256 단방향 암호화

### Frontend
- Thymeleaf
- JavaScript
- Bootstrap

### External API
- Aladin Open API (도서 검색)
- Daum Postcode Service API (주소 자동완성)
- PortOne API (결제 처리)

### Infra
- AWS EC2
- Git / GitHub

---

## 📌 주요 기능

### 1. 도서 검색
- Aladin Open API를 활용한 도서 검색 기능 구현
- 검색 결과 페이징 처리
- 검색 조건에 따른 동적 데이터 렌더링

### 2. 회원가입 / 로그인
- SHA-256 Hashing 방식을 적용한 비밀번호 단방향 암호화
- 기존 회원 정보 기준 중복 아이디 가입 방지
- 이메일 선택 입력 방식으로 유효성 보완

### 3. 장바구니
- 도서 정보를 한눈에 확인할 수 있도록 화면 구성
- 수량 변경 시 합계 금액 및 결제 요약 정보 즉시 반영
- 삭제 시 장바구니 목록과 결제 정보 동기화 처리

### 4. 주문 / 결제
- 주문 정보 DB 저장 처리
- PortOne API를 활용한 결제 연동
- 배송비, 할인, 적립 포인트 계산 로직 구현
- 주문 완료 후 장바구니 초기화


---

## 🧠 트러블슈팅

### 1️⃣ 주간 베스트셀러 조회 시 1주차가 0주차로 표시되는 문제
- 외부 API의 주차 계산 기준 분석
- 주차 계산 로직 수정으로 실제 기준과 일치하도록 개선

### 2️⃣ 비밀번호 암호화 처리
- 평문 저장 대신 SHA-256 단방향 암호화 적용
- 원본 데이터 복원이 불가능한 구조로 보안성 강화

---


## 📂 프로젝트 구조

```
com.jhb0430.nunubooks
 ├── books
 ├── cart
 ├── order
 ├── user
 ├── config
 └── common
```


- Controller → Service → Repository 계층 구조 적용
- Interceptor를 활용한 로그인 인증 처리 구현
- WebClient 기반 외부 API 연동


---

## 🎯 프로젝트 목표

- Spring 기반 웹 애플리케이션의 전체 동작 흐름 이해
- 외부 API 연동 경험
- 보안 처리(비밀번호 암호화) 구현
- AWS 배포 경험 확보
