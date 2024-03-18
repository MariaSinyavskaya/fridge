-- liquibase formatted sql

-- changeset msinyavskaya:1
create table users(
    id bigserial primary key,
    name VARCHAR(255) not null,
    password VARCHAR(255) not null
);