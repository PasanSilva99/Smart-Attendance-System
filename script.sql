create table attendance
(
    nsbm_id            varchar(15)   not null,
    device_mac_address varchar(100)  not null,
    marks              decimal(3, 3) null,
    router_mac_address varchar(100)  null
);

create table batch
(
    batch_number varchar(20) null
);

create table content
(
    question_number int      not null
        primary key,
    question        longtext null,
    correct_answer  int      null
);

create table degree_program
(
    degree_code varchar(10) not null
        primary key,
    degree_name varchar(45) null
);

create table device
(
    mac_address varchar(200) not null
        primary key,
    device_name varchar(45)  null,
    owner_email varchar(100) null
);

create table device_ip
(
    device_mac_address varchar(100)                        not null
        primary key,
    device_ip_address  varchar(45)                         null,
    date               timestamp default CURRENT_TIMESTAMP null,
    logged_email       varchar(100)                        null
);

create table event
(
    event_id    varchar(15)  not null
        primary key,
    event_name  varchar(200) null,
    module_code varchar(25)  null,
    start_time  datetime     null,
    end_time    datetime     null,
    lecturer    varchar(200) null,
    batch       varchar(30)  null,
    event_type  varchar(40)  null,
    location    varchar(20)  null
);

create table location
(
    code               varchar(20)  not null
        primary key,
    router_mac_address varchar(100) null
);

create table module
(
    module_code    varchar(15)  not null
        primary key,
    module_name    varchar(100) null,
    lecturer_name  varchar(100) null,
    degree_program varchar(100) null
);

create table module_set
(
    degree_code varchar(25) null,
    module_code varchar(50) null
);

create table question_answers
(
    q_number   varchar(20) not null
        primary key,
    question   longtext    null,
    answerList longtext    null,
    quiz_id    varchar(15) null
);

create table quiz
(
    quiz_id   varchar(15) not null,
    quiz_name varchar(45) null,
    event_id  varchar(15) null
);

create table user
(
    nsbm_id         varchar(45)  not null
        primary key,
    prefix          varchar(7)   null,
    name            varchar(100) null,
    nsbm_email      varchar(100) not null,
    password_hash   varchar(255) null,
    degree_program  varchar(80)  null,
    batch           varchar(45)  null,
    privilege_level varchar(45)  null
);


