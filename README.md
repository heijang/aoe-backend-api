# Atlas of Emotions - Backend API

감정 지도(Atlas of Emotions) 프로젝트의 백엔드 API 서버입니다.

## 기술 스택

- **Java**: 21
- **Spring Boot**: 3.5.7
- **빌드 도구**: Gradle
- **데이터베이스**: H2 (개발), PostgreSQL (프로덕션)
- **ORM**: Spring Data JPA
- **기타**:
  - Spring Web
  - Spring WebSocket
  - Spring Validation
  - Lombok

## 프로젝트 구조

```
src/main/java/com/emotions/
├── Application.java              # Spring Boot 애플리케이션 진입점
├── controller/                   # 컨트롤러 계층 (REST API 엔드포인트)
│   ├── UserController.java
│   └── TestController.java
├── service/                      # 서비스 계층 (비즈니스 로직)
│   └── UserService.java
├── repository/                   # 리포지토리 계층 (데이터 접근)
│   └── UserRepository.java
├── dto/                         # DTO/Entity 계층 (데이터 전송 객체)
│   └── UserDto.java
└── config/                      # 설정 파일

src/main/resources/
├── application.yml              # 애플리케이션 설정
├── static/                      # 정적 리소스
└── templates/                   # 템플릿 파일
```

### 아키텍처 설명

이 프로젝트는 **계층화된 아키텍처(Layered Architecture)** 패턴을 따릅니다:

#### 1. Controller 계층
- **역할**: HTTP 요청을 받아 처리하고 응답을 반환
- **위치**: `controller/` 패키지
- **주요 어노테이션**: `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping` 등
- **예시**: `UserController.java:12` - 사용자 관련 REST API 엔드포인트 제공

#### 2. Service 계층
- **역할**: 비즈니스 로직을 처리
- **위치**: `service/` 패키지
- **주요 어노테이션**: `@Service`
- **예시**: `UserService.java:10` - 사용자 생성, 조회, 수정, 삭제 등의 비즈니스 로직 구현

#### 3. Repository 계층
- **역할**: 데이터베이스와의 상호작용 담당
- **위치**: `repository/` 패키지
- **주요 어노테이션**: `@Repository`
- **특징**: Spring Data JPA의 `JpaRepository`를 상속받아 기본 CRUD 메서드 자동 제공
- **예시**: `UserRepository.java:10` - `JpaRepository`를 확장하여 사용자 데이터 접근

#### 4. DTO/Entity 계층
- **역할**: 데이터베이스 테이블과 매핑되는 엔티티 정의
- **위치**: `dto/` 패키지
- **주요 어노테이션**: `@Entity`, `@Table`, `@Id` 등
- **특징**: Lombok을 사용하여 보일러플레이트 코드 최소화 (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`)
- **예시**: `UserDto.java:8` - 사용자 엔티티 정의

## 실행 방법

### 사전 요구사항

- Java 21 이상 설치
- Git 설치

### 1. 프로젝트 클론

```bash
git clone <repository-url>
cd aoe-backend-api
```

### 2. 프로젝트 빌드

**Linux/macOS:**
```bash
./gradlew build
```

**Windows:**
```bash
gradlew.bat build
```

### 3. 애플리케이션 실행

**방법 1: Gradle을 사용한 실행**
```bash
./gradlew bootRun
```

**방법 2: JAR 파일 실행**
```bash
# 먼저 빌드
./gradlew build

# JAR 파일 실행
java -jar build/libs/backend-atlas-of-emotions-0.0.1-SNAPSHOT.jar
```

### 4. 애플리케이션 확인

서버가 성공적으로 실행되면 다음 주소로 접속할 수 있습니다:

- **API 서버**: http://localhost:8080
- **H2 데이터베이스 콘솔**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: (비어있음)

### 5. API 테스트

**테스트 엔드포인트:**
```bash
curl http://localhost:8080/users/test
# 응답: Hello World
```

**모든 사용자 조회:**
```bash
curl http://localhost:8080/users
```

**사용자 생성:**
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser", "email": "test@example.com"}'
```

**특정 사용자 조회:**
```bash
curl http://localhost:8080/users/1
```

**사용자 정보 수정:**
```bash
curl -X PUT http://localhost:8080/users/1 \
  -H "Content-Type: application/json" \
  -d '{"username": "updateduser", "email": "updated@example.com"}'
```

## 개발 모드

개발 중에는 다음 설정이 활성화되어 있습니다:

- **H2 콘솔**: 웹 브라우저에서 데이터베이스 확인 가능
- **SQL 로깅**: 실행되는 SQL 쿼리를 콘솔에서 확인 가능
- **DDL Auto**: 애플리케이션 시작 시 테이블 자동 생성/삭제 (`create-drop`)

## 테스트

```bash
./gradlew test
```

## 빌드 산출물 정리

```bash
./gradlew clean
```

## 데이터베이스 설정

### H2 (기본 - 개발용)
현재 설정은 인메모리 H2 데이터베이스를 사용합니다. 애플리케이션 재시작 시 데이터가 초기화됩니다.

### PostgreSQL (프로덕션)
프로덕션 환경에서는 `application.yml`을 다음과 같이 수정하여 PostgreSQL을 사용할 수 있습니다:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/your_database
    driver-class-name: org.postgresql.Driver
    username: your_username
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
```

## 프로젝트 정보

- **Group**: com.emotions
- **Artifact**: backend-atlas-of-emotions
- **Version**: 0.0.1-SNAPSHOT
- **Description**: backend-atlas-of-emotions

## 라이선스

이 프로젝트의 라이선스 정보는 별도로 명시되어 있지 않습니다.