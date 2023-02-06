
create table winter_users.users
(
    id bigserial primary key,
    username varchar(36) not null,
    password varchar(80) not null,
    email varchar(255) ,
    phone varchar(255) ,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp

);

create table winter_users.roles
(
    id bigserial primary key,
    name_role varchar(50) not null
);

create table winter_users.user_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into winter_users.roles (name_role) values ('ROLE_USER'), ('ROLE_ADMIN');

insert into winter_users.users (username, password)
values
    ('Bob', '$2a$12$vpmdxQ3M8lXB63SWHDkIMuwJYqIkqRZTrKozEZazguAEOTj/IzkcK'),
    ('John', '$2a$12$vpmdxQ3M8lXB63SWHDkIMuwJYqIkqRZTrKozEZazguAEOTj/IzkcK');

insert into winter_users.user_roles (user_id, role_id) values (1,1), (2,2);
