create sequence message_seq start with 1 increment by 50;
create sequence usr_seq start with 1 increment by 50;

create table message (
    id integer not null,
    user_id bigint,
    text varchar(2048) not null,
    tag varchar(255),
    primary key (id)
);

create table user_role (
    user_id bigint not null,
    roles varchar(255) check (roles in ('USER'))
);

create table usr (
    id bigint not null,
    username varchar(255) not null,
    password varchar(255) not null,
    email varchar(255),
    activation_code varchar(255),
    active boolean not null,
    primary key (id)
);

alter table if exists message
    add constraint msg_user_fk foreign key (user_id) references usr

alter table if exists user_role
    add constraint role_user_fk foreign key (user_id) references usr