# MyGoodSupporter DDL

## Schema

## 변경 사항

### 2021.07.21

#### 1. Drop Table 구문 제거

`schema.sql` 파일은 데이터베이스에 테이블을 생성할 때 사용한다. Drop Table, Drop Sequence 구문을 `drop.sql`파일로 이동시킨다.

#### 2. `create_user.sql` > `initialize.sql` 이름 변경

`create_user.sql`은 데이터베이스를 추가하고 사용자를 추가하는 스크립트이다. 파일 이름이 내용을 제대로 반영하지 못하므로 파일명을 변경했다.

#### 3. `database/scripts` 폴더 추가

`initilize.sql`, `drop.sql` 파일은 데이터베이스를 수정하는 스크립트이다. 폴더를 하나 만들어서 모아주었다.