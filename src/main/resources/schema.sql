create table application_user (
id bigint not null constraint application_user_pkey primary key,
email varchar(255) not null,
enabled boolean not null,
locked boolean not null,
name varchar(255) not null,
password varchar(255) not null,
user_role varchar(255) not null
);

create table author (
id bigint not null constraint author_pkey primary key,
biography varchar(255),
name varchar(50) not null,
surname varchar(50) not null
);

create table category(
    id bigint not null constraint category_pkey primary key,
    name varchar(50) not null
);

create table publishing (
id bigint not null constraint publishing_pkey primary key,
building varchar(255),
city varchar(255),
postal_code varchar(255),
street varchar(255),
name varchar(50) not null,
phone varchar(50) not null constraint publishing_phone_unique unique,
website varchar(50) not null constraint publishing_website_unique unique
);

create table verification_token(
id bigint not null constraint verification_token_pkey primary key,
confirmed_at timestamp,
created timestamp not null,
expired timestamp not null,
token varchar(255) not null,
user_id bigint constraint application_user_fk references application_user
);

create table order_book (
id bigint not null constraint order_book_pkey primary key,
quantity integer not null,
book_id bigint not null constraint book_order_fk references book,
user_id bigint not null constraint user_order_fk references application_user
);

create table book (
id bigint not null constraint book_pkey primary key,
age_limit varchar(255) not null,
availability varchar(255) not null,
cover varchar(255) not null,
format varchar(255) not null,
isbn varchar(255) not null constraint book_isbn_unique unique,
pages integer not null,
price numeric(19, 2) not null,
rating integer not null,
release_date integer not null,
title varchar(255) not null,
weight double precision not null,
author_id bigint not null constraint book_author_fk references author,
category_id bigint not null constraint book_category_fk references category,
publishing_id bigint not null constraint book_publishing_fk references publishing
);

