### 1. servlet 생명주기 순서
    1. Servlet 클래스 로드 : Load Servlet Class
    2. Servlet 클래스 인스턴스 생성 : Create Servlet Instance
    3. Servlet 인스턴스 초기화 : Call init()
    4. 웹 컨테이너에 의한 서비스 메서드 호출 : Call Service
    5. destroy 메서드를 호출하여 Servlet 종료 : Call destroy()
### 2. GROUPBY
### 3. DML, DDL 명령어 구분
    - DML(Data Manipulation Language)
        : SELECT, INSERT, UPDATE, DELETE
    - DDL(Data Definition Language)
        : CREATE, ALTER, DROP, RENAME
    - DCL(Data Control Language)
    - TCL(Transaction Control Language)
### 4. 외래키
    - FOREIGN KEY
### 5. 제약조건
    - PRIMARY KEY
    - FOREIGN KEY
    - NOT NULL
    - UNIQUE
    - CHECK
    - DEFAULT
### 6. 날짜, 시간 반환 함수
    - now()
    - CURRENT_TIMESTAMP
    - SYSDATE()
### 7. 테이블 지우기
    - DROP {DATABASE} [IF EXISTS] db_name
    - IF EXISTS는 데이터베이스가 없을 시 발생할 수 있는 에러 방지
### 8. 테이블 구조 확인
    - DESCRIBE {테이블}
    - DESC {테이블}
### 9. .getContextPath
### 10. 와일드 카드(%, _) 차이
    - % : 0개 이상의 문자
    - _ : 문자 하나를 의미
