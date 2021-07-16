create table member
(
    M_ID varchar(255) not null,
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
