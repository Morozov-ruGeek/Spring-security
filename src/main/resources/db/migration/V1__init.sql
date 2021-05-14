create table roles
(
    id   bigserial PRIMARY KEY not null,
    name varchar(255)
);

create table authorities
(
    id   bigserial PRIMARY KEY not null,
    name varchar(255)
);

create table users
(
    id       bigserial PRIMARY KEY not null,
    name     varchar(255),
    password varchar(255)
);

create table users_roles
(
    user_id bigint,
    role_id bigint,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

create table roles_authorities
(
    role_id      bigint,
    authority_id bigint,
    primary key (role_id, authority_id),
    foreign key (role_id) references roles (id),
    foreign key (authority_id) references authorities (id)
);

create table users_authorities
(
    user_id      bigint,
    authority_id bigint,
    primary key (user_id, authority_id),
    foreign key (user_id) references users (id),
    foreign key (authority_id) references authorities (id)
);

insert into roles (name)
values ('ROLE_ADMIN'),
       ('ROLE_MANAGER'),
       ('ROLE_USER');

insert into authorities (name)
values ('ADMIN_PANEL'),
       ('ADD_USER'),
       ('VIEW_USER'),
       ('ADD_PRODUCT'),
       ('DELETE_PRODUCT'),
       ('CHANGE_USERS_AUTHORITIES'),
       ('MANAGER_PANEL'),
       ('EDIT_PRODUCT_PRICE'),
       ('PRODUCT_LIST');

insert into users (name, password)
values ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
       ('User2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
       ('User3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

insert into roles_authorities (role_id, authority_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (1, 9),
       (2, 7),
       (2, 8),
       (2, 9),
       (3, 9);

insert into users_authorities (user_id, authority_id)
values (2, 4),
       (2, 5),
       (3, 4);

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 3);