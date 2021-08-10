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
    start_date date null,
    end_date date null,
    title_image_url varchar(500) not null,
    contents_image_url varchar(500) null,
    constraint project_member_m_id_fk
        foreign key (user_id) references user (id)
);

create table cardinfo
(
    id bigint auto_increment
        primary key,
    card_number varchar(255) null,
    expired_date varchar(255) null,
    card_password varchar(255) null,
    date_of_birth varchar(255) null,
    userId varchar(255) null
);