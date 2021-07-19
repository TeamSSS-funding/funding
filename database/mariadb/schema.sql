create table member
(
    M_ID varchar(255) PRIMARY KEY ,
    M_PASSWORD varchar(255) null,
    M_NAME varchar(255) null,
    M_PHONE varchar(255) null,
    M_EMAIL varchar(500) null,
    M_TYPE varchar(255) null,
    ENABLED bit default b'1' null,
    AUTHORITY varchar(255) default 'ROLE_MEMBER' null,
    CITY varchar(255) null,
    STREET varchar(255) null,
    ZIPCODE varchar(255) null
);

-- 프로젝트 테이블
create table project
(
    P_NUMBER int(10) not null
        primary key,
    P_M_ID varchar(255) not null,
    P_NAME varchar(255) not null,
    P_PRICE int(10) not null,
    P_CONTENTS varchar(255) not null,
    P_STARTDATE date not null,
    P_ENDDATE date not null,
    P_IMGNAME varchar(255) not null,
    constraint project_member_M_ID_fk
        foreign key (P_M_ID) references member (M_ID)
);

-- 프로젝트 시퀀스
CREATE SEQUENCE PROJECT_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

