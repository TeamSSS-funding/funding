create table MEMBER
(
    M_ID NVARCHAR2(50) not null
        constraint PK_FUNDING
            primary key,
    M_PASSWORD NVARCHAR2(50),
    M_NAME NVARCHAR2(50),
    M_PHONE NVARCHAR2(50),
    M_EMAIL NVARCHAR2(500),
    M_TYPE NVARCHAR2(50),
    ENABLED NUMBER default 1,
    AUTHORITY NVARCHAR2(255) default 'ROLE_MEMBER'
);