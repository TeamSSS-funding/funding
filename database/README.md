# MyGoodSupporter DDL

## Schema

1. 테이블명/컬럼명/키워드 모두 소문자로 작성!
2. 날짜 - date 타입으로 사용
3. varchar(255) 기본 -> 필요하면 늘리기
4. null 인 항목은 제약조건 생략
## 변경 사항
1. Simsa테이블 이름 => Proposal으로 변경함

### 2021.07.21

#### 1. Drop Table 구문 제거

`schema.sql` 파일은 데이터베이스에 테이블을 생성할 때 사용한다. Drop Table, Drop Sequence 구문을 `drop.sql`파일로 이동시킨다.

#### 2. `create_user.sql` > `initialize.sql` 이름 변경

`create_user.sql`은 데이터베이스를 추가하고 사용자를 추가하는 스크립트이다. 파일 이름이 내용을 제대로 반영하지 못하므로 파일명을 변경했다.

#### 3. `database/scripts` 폴더 추가

`initilize.sql`, `drop.sql` 파일은 데이터베이스를 수정하는 스크립트이다. 폴더를 하나 만들어서 모아주었다.

#### 4. `schema.sql` 현재 개발 중인 테이블과 나중에 추가할 테이블 분리

나중에 개발할 기능의 테이블 정보까지 `schema.sql`안에 들어있어 데이터베이스를 조회하기 힘들고 작업 진척내용을 파악하기 힘들다.
`schema.sql`에는 작업이 완료된 테이블과 개발 중인 테이블만 들어가야한다. 현재 작업하지 않는 테이블의 스키마는 `database/future.sql` 파일에 따로 빼두었다.
