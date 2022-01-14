create database if not exists ann_db;
use ann_db;

create table if not exists users
(
    id int auto_increment not null,
    id_client int,
    login varchar(45) not null,
    password varchar(30) not null,
    role varchar(30),
    primary key(id)
);
create table if not exists client
(
    id int auto_increment not null,
    id_user int,
    name varchar(30),
    surname varchar(30),
    primary key(id),
    foreign key (id_user) references users(id)
);
alter table users add foreign key (id_client) references client(id) on update cascade on delete cascade;
create table if not exists car
(
    id int auto_increment not null,
    vin varchar(40),
    model varchar(40),
    color varchar(30),
    price int,
    primary key (id)
);
create table if not exists order_car
(
    id int auto_increment not null,
    id_car int,
    created_at datetime,
    id_client int,
    primary key(id),
    foreign key(id_client) references client(id) on delete cascade,
    foreign key(id_car) references car(id) on delete cascade
);

create table if not exists comment
(
    id int auto_increment not null,
    id_client int,
    topic varchar(30),
    comment_text varchar(256) not null,
    estimation int,
    primary key(id),
    foreign key(id_client) references client(id) on delete cascade
);
create table if not exists insurance
(
    id int auto_increment not null,
    insurance_type varchar(40),
    id_client int,
    primary key(id),
    foreign key(id_client) references client(id) on delete cascade
);

insert into users(login, password, role) values ('admin','admin','Admin');
insert into users(login, password, role) values ('user','user','User');
insert into client(id_user, name, surname) values (2,'Bob','Martin');

use ann_db;
update users
set id_client = 1
where id = 2;