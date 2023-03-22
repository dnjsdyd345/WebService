Gradle-version : 7.6.1
SpringBoot-version : 2.7.10-SNAPSHOT

    * API 테스트 및 명세 확인을 위한 URL *
        ip:port/swagger-ui/index.html (ex. localhost:8080/swagger-ui/index.html )

    * h2 접속 *
        ip:port/h2-console (ex. localhost:8080/h2-console)
        Saved Setting: Generic H2(Embedded)
        Setting Name : Generic H2 (Embedded)
        Driver Class : org.h2.Driver
        JDBC URL     : jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE
        User Name    : sa

- 추가 repository
    - SWAGGER :
        API 테스트 화면제공, API 명세를 위해 사용
    - querydsl, annotationProcessor :
        코드로 쿼리를 작성할 경우를 위해 사용 및 빌드 단계에서 에러 확인
    - Lombok :
        dto 클래스 작성
    - json :
        json parsing

- API
    - KAKAO API 조회
    - KAKAO API 조회 후 h2 db에 검색어, 빈도 수, 날짜, Key(Sequence) 저장.
        - Pagination (default page : 0, size :10) Parameter로 선택적으로 가능
    - 검색로그 delete to primary key

