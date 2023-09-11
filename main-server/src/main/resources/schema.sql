create table if not exists users
(
    id    bigint generated always as identity,
    name  varchar(250) not null,
    email varchar(254) not null,
    constraint pk_users primary key (id),
    constraint uq_users_email unique (email)
    );

create table if not exists categories
(
    id   bigint generated always as identity,
    name varchar(50) not null,
    constraint pk_categories primary key (id),
    constraint uq_categories_name unique (name)
    );
