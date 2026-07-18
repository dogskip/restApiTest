# restApiTest

Spring Boot 3 기반의 간단한 사용자 REST API 예제입니다. JPA로 사용자를 저장하고,
Spring Security HTTP Basic 인증으로 `/api/users` 엔드포인트를 보호합니다.

## 요구 사항

- Java 17 이상
- 애플리케이션 실행 시 MariaDB

## 데이터베이스 설정과 실행

Spring Boot 표준 환경 변수로 MariaDB 연결 정보를 전달합니다.

```bash
export SPRING_DATASOURCE_URL='jdbc:mariadb://localhost:3306/rest_api_test'
export SPRING_DATASOURCE_USERNAME='app_user'
export SPRING_DATASOURCE_PASSWORD='change-me'
./gradlew bootRun
```

별도 사용자 설정이 없으면 Spring Security의 기본 사용자 `user`와 시작 로그에
출력되는 임시 비밀번호를 사용합니다.

## API

모든 사용자 API는 HTTP Basic 인증이 필요합니다.

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/api/users` | 모든 사용자 조회 |
| `POST` | `/api/users` | 사용자 생성 |

Swagger UI는 애플리케이션 실행 후 `/swagger-ui/index.html`에서 확인할 수 있습니다.

## 테스트

테스트는 내장 H2 데이터베이스를 사용하므로 MariaDB 없이 실행할 수 있습니다.

```bash
./gradlew test
```
# restApiTest
