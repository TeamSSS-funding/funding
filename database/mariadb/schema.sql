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
    EMAILID varchar(255) null,
    DOMAIN varchar(255) null,

);

create table authority (
  M_ID varchar(255),
  AUTHORITY varchar(255)
);

-- 메이커정보 테이블
CREATE TABLE INFOMAKER(
                          I_M_ID varchar(255) primary key,
                          I_CONTENTS varchar(255) NOT NULL,
                          I_PROFILENAME varchar(255) NOT NULL,
                          CONSTRAINT FK_INFOMAKER foreign key (I_M_ID) REFERENCES MEMBER(M_ID) ON DELETE CASCADE
);

-- 배송지 테이블
CREATE TABLE ADDRESS(
                        A_NUMBER int(10) PRIMARY KEY,
                        A_M_ID varchar(255) NOT NULL,
                        A_NAME varchar(255) NOT NULL,
                        A_PHONE varchar(255) NOT NULL,
                        A_POST varchar(255) NOT NULL,
                        A_ADDRESS1 varchar(255) NOT NULL,
                        A_ADDRESS2 varchar(255) NOT NULL,
                        A_ADDRESS3 varchar(255) NOT NULL,
                        A_ADDRESS4 varchar(255) NOT NULL,
                        CONSTRAINT FK_ADDRESS foreign key (A_M_ID) REFERENCES MEMBER(M_ID) ON DELETE CASCADE
);

-- 배송지 시퀀스
CREATE SEQUENCE ADDRESS_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

-- 배송현황 테이블
CREATE TABLE DELIVERY(
                         D_NUMBER int(10) PRIMARY KEY,
                         D_P_NUMBER int(10) not null,
                         D_STATE VARCHAR(255) NOT NULL,
                         CONSTRAINT FK_DELIVERY foreign key (D_P_NUMBER) REFERENCES PROJECT(P_NUMBER) ON DELETE CASCADE
);

-- 배송현황 시퀀스
CREATE SEQUENCE DELIVERY_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

-- 찜 테이블
CREATE TABLE ZZIM(
     Z_NUMBER int(10) PRIMARY KEY,
     Z_M_ID VARCHAR(255) not null,
     Z_P_NUMBER int(10)  not null,
     CONSTRAINT FK_ZZIM_ID foreign key (Z_M_ID) REFERENCES MEMBER(M_ID) ON DELETE CASCADE,
     CONSTRAINT FK_ZZIM_PNUM foreign key (Z_P_NUMBER) REFERENCES PROJECT(P_NUMBER) ON DELETE CASCADE
);

-- 찜 시퀀스
CREATE SEQUENCE ZZIM_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

-- 쿠폰 테이블
CREATE TABLE KUPON(
  K_NUMBER int(10) PRIMARY KEY,
  K_NAME VARCHAR(255) NOT NULL,
  K_PRICE int(10) NOT NULL);

-- 쿠폰 시퀀스
CREATE SEQUENCE KUPON_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

-- 회원 쿠폰 테이블
CREATE TABLE KUPONMEMBER(
    KM_K_NUMBER int(10) not null,
    KM_M_ID varchar(255) not null,
    KM_STATE varchar(255) NOT NULL,
    CONSTRAINT FK_KUPONMEMBER_NUM foreign key (KM_K_NUMBER) REFERENCES KUPON(K_NUMBER) ON DELETE CASCADE,
    CONSTRAINT FK_KUPONMEMBER_ID foreign key (KM_M_ID) REFERENCES MEMBER(M_ID) ON DELETE CASCADE
);

-- 카테고리 테이블
CREATE TABLE KATEGORY(
    C_NUMBER int(10) PRIMARY KEY,
    C_NAME VARCHAR(255) NOT NULL);

-- 카테고리 시퀀스
CREATE SEQUENCE KATEGORY_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

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

-- 옵션 테이블
CREATE TABLE OPTIONS(
    O_NUMBER int(10) PRIMARY KEY,
    O_P_NUMBER int(10) not null,
    O_NAME VARCHAR(255) NOT NULL,
    O_PRICE int(10) NOT NULL,
    O_CONTENTS VARCHAR(255) NOT NULL,
    O_STOCK int(10) NOT NULL,
    CONSTRAINT FK_OPTION_PNUM foreign key (O_P_NUMBER) REFERENCES PROJECT(P_NUMBER) ON DELETE CASCADE
);

-- 옵션 시퀀스
CREATE SEQUENCE OPTION_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

-- 심사 테이블
CREATE TABLE SIMSA(
      S_NUMBER int(10) PRIMARY KEY,
      S_P_NUMBER int(10) not null,
      S_RESULT VARCHAR(255) NOT NULL,
      S_REASON VARCHAR(255) NOT NULL,
      CONSTRAINT FK_SIMSA foreign key (S_P_NUMBER) REFERENCES PROJECT(P_NUMBER) ON DELETE CASCADE
);

-- 심사 시퀀스
CREATE SEQUENCE SIMSA_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

-- 주문 테이블
CREATE TABLE JUMUN(
      J_NUMBER int(10) PRIMARY KEY,
      J_M_ID VaRCHAR(255) not null,
      J_O_NUMBER int(10) not null,
      J_TIME DATE NOT NULL,
      CONSTRAINT FK_JUMUN_ID foreign key (J_M_ID) REFERENCES MEMBER(M_ID) ON DELETE CASCADE,
      CONSTRAINT FK_JUMUN_ONUM foreign key (J_O_NUMBER) REFERENCES OPTIONS(O_NUMBER) ON DELETE CASCADE
);

-- 주문 시퀀스
CREATE SEQUENCE JUMUN_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

-- 결제 정보 테이블
CREATE TABLE WON(
    W_NUMBER int(10) PRIMARY KEY,
    W_J_NUMBER int(10) not null,
    W_K_NUMBER int(10) not null,
    W_PRICE int(10) NOT NULL,
    CONSTRAINT FK_WON_INUM foreign key (W_J_NUMBER) REFERENCES JUMUN(J_NUMBER) ON DELETE CASCADE,
    CONSTRAINT FK_WON_KNUM foreign key (W_K_NUMBER) REFERENCES KUPON(K_NUMBER) ON DELETE CASCADE
);

-- 결제 시퀀스
CREATE SEQUENCE WON_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

-- 메이커예약 테이블
CREATE TABLE MAK_BOOKING(
    MB_NUMBER int(10) PRIMARY KEY,
    MB_P_NUMBER int(10) not null,
    MB_DATE DATE NOT NULL,
    MB_START DATE NOT NULL,
    MB_END DATE NOT NULL,
    MB_LIMIT int(10) NOT NULL,
    CONSTRAINT FK_MAK_BOOKING_PNUM foreign key (MB_P_NUMBER) REFERENCES PROJECT(P_NUMBER) ON DELETE CASCADE
);

-- 메이커예약 시퀀스
CREATE SEQUENCE MAK_BOOKING_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

-- 예약 테이블
CREATE TABLE BOOKING(
    b_number int(10) PRIMARY KEY,
    b_m_id VARCHAR(255) not null,
    b_p_number int(11) not null,
    b_date DATE NOT NULL,
    b_time DATE NOT NULL,
	CONSTRAINT FK_BOOKING_ID foreign key (b_m_id) REFERENCES MEMBER(M_ID) ON DELETE CASCADE,
                                                                                                                         CONSTRAINT FK_BOOKING_PNUM foreign key (b_p_number) REFERENCES PROJECT(P_NUMBER) ON DELETE CASCADE
                                                                                                                         );

-- 예약 시퀀스
DROP SEQUENCE BOOKING_SEQ;
CREATE SEQUENCE BOOKING_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

-- 알림 테이블
CREATE TABLE NOTICE(
                       N_NUMBER int(10) PRIMARY KEY,
                       N_P_NUMBER int(10) not null,
                       N_M_ID VARCHAR(255) not null,
                       N_CONTENTS VARCHAR(255) NOT NULL,
                       CONSTRAINT FK_NOTICE_PNUM foreign key (N_P_NUMBER) REFERENCES PROJECT(P_NUMBER) ON DELETE CASCADE,
                       CONSTRAINT FK_NOTICE_ID foreign key (N_M_ID) REFERENCES MEMBER(M_ID) ON DELETE CASCADE
);

-- 알림 시퀀스
CREATE SEQUENCE NOTICE_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

-- 검색어 테이블
CREATE TABLE FIND(
    F_NUMBER int(10) PRIMARY KEY,
    F_COUNT int(10) NOT NULL,
    F_CONTENTS NVARCHAR2(50) NOT NULL);

-- 검색어 시퀀스
CREATE SEQUENCE FIND_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

-- 문의 테이블
CREATE TABLE QNA(
    Q_NUMBER int(10) PRIMARY KEY,
    Q_M_ID VARCHAR(255) not null,
    Q_P_NUMBER int(10) not null,
    Q_TITLE VARCHAR(255) NOT NULL,
    Q_CONTENTS VARCHAR(255) NOT NULL,
    Q_DATE DATE NOT NULL,
    CONSTRAINT FK_QNA_ID foreign key (Q_M_ID) REFERENCES MEMBER(M_ID) ON DELETE CASCADE,
    CONSTRAINT FK_QNA_PNUM foreign key (Q_P_NUMBER) REFERENCES PROJECT(P_NUMBER) ON DELETE CASCADE
);

-- 문의 시퀀스
CREATE SEQUENCE QNA_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

-- 리뷰 테이블
CREATE TABLE REVIEW(
   R_NUMBER int(10) PRIMARY KEY,
   R_M_ID VARCHAR(255) not null,
   R_P_NUMBER int(10) not null,
   R_DATE DATE NOT NULL,
   R_CONTENTS VARCHAR(255) NOT NULL,
   CONSTRAINT FK_REVIEW_ID foreign key (R_M_ID) REFERENCES MEMBER(M_ID) ON DELETE CASCADE,
   CONSTRAINT FK_REVIEW_PNUM foreign key (R_P_NUMBER) REFERENCES PROJECT(P_NUMBER) ON DELETE CASCADE
);

-- 리뷰 시퀀스
CREATE SEQUENCE REVIEW_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;

-- 리리뷰 테이블
CREATE TABLE REREVIEW(
     RR_NUMBER int(10) PRIMARY KEY,
     RR_R_NUMBER int(10) not null,
     RR_M_ID VARCHAR(255) not null,
     RR_DATE DATE NOT NULL,
     RR_CONTENTS VARCHAR(255) NOT NULL,
     CONSTRAINT FK_REREVIEW_RNUM foreign key (RR_R_NUMBER) REFERENCES REVIEW(R_NUMBER) ON DELETE CASCADE,
     CONSTRAINT FK_REREVIEW_ID foreign key (RR_M_ID) REFERENCES MEMBER(M_ID) ON DELETE CASCADE
);

-- 리리뷰 시퀀스
CREATE SEQUENCE REREVIEW_SEQ
    START WITH 100
    INCREMENT BY 1
    NOCACHE;
