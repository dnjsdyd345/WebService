Gradle-version : 7.6.1
SpringBoot-version : 2.7.10-SNAPSHOT

    * API 테스트 및 명세 확인을 위한 URL *
    ip:port/swagger-ui/index.html (ex. localhost:8080/swagger-ui/index.html )

- 추가 repository
    - SWAGGER :
        API 테스트 화면제공, API 명세를 위해 사용
    - querydsl, annotationProcessor :
        코드로 쿼리를 작성할 경우를 위해 사용 및 빌드 단계에서 에러 확인
    - Lombok :
        dto 클래스 작성
    - json :
        json parsing
