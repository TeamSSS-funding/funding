create table member
(
    m_id       varchar(255)     not null,
    m_password varchar(255)     not null,
    m_name     varchar(255)     not null,
    m_phone    varchar(255)     not null,
    m_email    varchar(500)     null,
    ENABLED    bit default b'1' null
);

create table authority
(
    m_id      varchar(255),
    authority varchar(255)
);

