# 📦 프로젝트 루트에 Dockerfile 하나 새로 만들기

# 1) JDK 17 기반 이미지
FROM eclipse-temurin:17-jdk

# 2) 작업 디렉토리
WORKDIR /app

# 3) 소스 전체 복사
COPY . .

# 4) gradlew 실행권한 주고, Spring Boot 실행 JAR 만들기
#   -x test : 지금은 테스트 건너뛰고 빌드 (빨리 보기용)
RUN chmod +x ./gradlew \
    && ./gradlew bootJar -x test

# 5) Render가 알려주는 PORT 쓰기 (기본값 8080)
ENV PORT=8080
EXPOSE ${PORT}

# 6) 스프링부트 실행 (prod 프로필은 Render 환경변수로 줄 거라 여기서 안 박음)
CMD ["sh", "-c", "java -jar build/libs/*SNAPSHOT*.jar --server.port=${PORT}"]
