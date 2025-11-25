Reminder (엔티티 = 테이블 정의)

ReminderRepository (JPA 리포지토리)

ReminderController (REST API)


src/main/resources/
├─ application.yml               # 공통 + active profile
├─ application-dev.yml           # 개발 환경
└─ application-prod.yml          # 운영 환경 (선택)

############################################

📌 Simple Reminder — 개발/배포/CI 구조 정리

이 프로젝트는 React + Spring Boot + H2 + GitHub Actions(CI) 로 구성된 단순 리마인더 앱입니다.
로컬 개발–자동 테스트–배포까지 이어지는 실무형 미니 파이프라인을 목표로 합니다.

🚀 1. 로컬 개발 환경
백엔드: Spring Boot

언어: Java 17

DB: H2 (file mode)

JPA/Hibernate 사용

개발 프로파일: dev

프론트엔드: React

vite 기반

포트: 5173

API 서버: 8080 호출

🗂️ 2. 프로파일 구조
application.yml (최상위)
spring:
profiles:
active: dev

application-dev.yml (로컬 개발용)

H2 file 모드 사용 (서버 꺼도 데이터 유지)

H2 콘솔 사용 가능

spring:
datasource:
url: jdbc:h2:file:./data/devdb;MODE=MYSQL;DB_CLOSE_DELAY=-1
driver-class-name: org.h2.Driver
username: sa
password:
jpa:
hibernate:
ddl-auto: update
show-sql: true
h2:
console:
enabled: true
path: /h2-console

server:
port: 8080

🧪 3. 테스트 환경 (CI 포함)

테스트는 스프링 컨텍스트 + H2 메모리 DB로 실행합니다.
→ 테스트 데이터는 매 실행마다 초기화됨
→ CI에서도 그대로 동작

application-test.yml
spring:
datasource:
url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
driver-class-name: org.h2.Driver
username: sa
password:
jpa:
hibernate:
ddl-auto: create-drop
h2:
console:
enabled: false


CI에서는 다음 명령으로 테스트 실행:

./gradlew clean test -Dspring.profiles.active=test

🤖 4. GitHub Actions — push 시 자동 테스트

.github/workflows/ci.yml

name: simple-reminder-ci

on:
push:
branches: [ main ]

jobs:
backend-tests:
runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v4

      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: '17'
          cache: gradle

      - name: Grant execute permission for gradlew
        run: chmod +x gradlew

      - name: Run tests (test profile)
        run: ./gradlew clean test -Dspring.profiles.active=test

동작 방식

main 브랜치로 push

GitHub Actions 실행

JDK 17 설치

테스트 실행

테스트 성공 시 → 초록불

테스트 실패 시 → 빨간불

→ 실무 CI 의 최소 형태를 그대로 경험할 수 있음.

📦 5. Git 관리 방식 (실무형)

main → 안정 버전

기능 작업 시:

feature/기능명 브랜치 생성

작업 후 commit & push

필요하면 PR 만들어서 CI 확인 후 merge

🚀 6. 앞으로 확장 가능성

이 프로젝트는 아래처럼 확장할 수 있습니다.

Fly.io 무료 서버 배포

Spring Boot JAR 업로드

외부에서도 접속 가능

Docker 이미지 만들기

Spring Boot + H2 포함

컨테이너 기반 배포 체험

프론트 배포 + Nginx

React build 결과물을 정적 파일 서버에서 제공

백엔드와 프론트 분리 운영

E2E 테스트 / React 테스트 추가

✔️ 요약

이 프로젝트는 아래 구조로 동작합니다:

로컬 개발: dev 프로파일

테스트/CI: test 프로파일

main push → 자동 테스트

H2 파일 DB는 Git에 올리지 않음 (실무 방식)

이 구조는 현업 스프링 + React 프로젝트에서 실제로 사용하는 패턴을 그대로 반영합니다.