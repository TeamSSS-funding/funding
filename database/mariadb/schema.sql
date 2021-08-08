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

create table project
(
    id bigint(20) primary key auto_increment,
    user_id bigint(20) not null,
    category_id bigint(2) not null,
    title varchar(255) null,
    subtitle varchar(255) not null,
    goal_amount int(10) null,
    current_amount int(10) null,
    status varchar(255) not null,
    start_date date,
    end_date date,
    constraint project_user_user_id_fk
        foreign key (user_id) references user (id),
    constraint project_category_id_fk
        foreign key (category_id) references category (id)
);

CREATE TABLE category (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL
);

create table item
(
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    project_id BIGINT(20),
    title varchar(255),
    constraint item_project_id_fk
        foreign key (project_id) references project (id)
            on delete cascade
);

create table card
(
    id bigint auto_increment primary key,
    user_id varchar(255) null,
    card_number varchar(255) null,
    expired_date varchar(255) null,
    card_password varchar(255) null,
    date_of_birth varchar(255) null
);