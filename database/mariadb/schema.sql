create table user
(
    id         bigint(20) PRIMARY KEY AUTO_INCREMENT,
    username       varchar(255)     UNIQUE not null,
    password varchar(255)     not null,
    name     varchar(255)     not null,
    phone    varchar(255)     not null,
    email    varchar(500)     null,
    ENABLED    bit default b'1' null
);

create table authority
(
    username      varchar(255),
    authority varchar(255)
);

CREATE TABLE proposal
(
    id            BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    member_id     VARCHAR(255) NOT NULL,
    title         VARCHAR(255) NOT NULL,
    description   VARCHAR(255) NOT NULL,
    target_amount INT(11)      NOT NULL,
    status        VARCHAR(255) NOT NULL
);

CREATE SEQUENCE project_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

create table project
(
    id bigint(20) primary key not null,
    user_id bigint(20) not null,
    name varchar(255) not null,
    slug varchar(255) not null,
    target_amount int(10) not null,
    current_amount int(10) not null,
    content varchar(255) not null,
    status varchar(255) not null,
    start_date date,
    end_date date,
    constraint project_member_m_id_fk
        foreign key (user_id) references user (id)
);