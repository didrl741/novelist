# ✏️ 초단편 소설가  

> 매일매일 랜덤 키워드 글쓰기 연습 웹 서비스
```
좀처럼 글 쓸 기회가 없는 요즘..
초단편 소설가와 함께 글쓰기 능력을 키워봐요!
[대상 사용자]
- 글을 써보고싶은데 무엇에 대해 써야할 지 모르겠는 분
- 부담없이 글쓰기 연습을 해보고싶은 분
```
* **배포 링크** : http://ec2-15-165-115-84.ap-northeast-2.compute.amazonaws.com:8080/

<br/>

## 사용 기술 

#### Core
* JAVA 17
* Spring Boot 2.7.2
* Spring Data JPA
* Spring Security + OAuth2
* Gradle 7.5

#### 배포
* AWS ec-2

#### Database
* Maria-db

#### 개발환경
* IntelliJ
* Github

#### Front-End
* Thymeleaf
* BootStrap

_____

<br/>

## simplified ERD

![erd](https://user-images.githubusercontent.com/97036481/223451853-abdd2005-1ced-4b99-a35a-7754b2a1ddcd.jpg)


<br/>

## 구현 기능

### 1. 회원관리

- `Spring Security` + `OAuth2`를 이용해서 소셜 로그인 기능 및 인증과 인가 구현
- `javax.mail`를 이용해서 이메일을 통한 비밀번호 찾기 기능 구현
- 회원정보 조회 및 변경 기능 구현

### 2. 랜덤키워드

- 약 4000개의 명사 중 매일 `@EnableScheduling`을 통해 5개의 랜덤 단어 추출
- `@WriteLimit`이라는 애노테이션을 직접 만들어서 사용자가 입력한 글이 선정된 5개의 단어를 포함하는지 검사하는 로직 구현

### 3. 게시글

- `AJAX` 를 통해 실시간 비동기 '좋아요' 기능 제공
- Pagination을 구현하여 페이징 기능 제공

<br/>

## 핵심 코드 정리 및 트러블슈팅

<br/>

## 그 외 프로젝트를 진행하며 경험하고 체득한 내용
