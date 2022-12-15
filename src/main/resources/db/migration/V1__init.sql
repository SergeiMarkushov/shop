create table winter_shop.products
(
    id bigserial primary key,
    title varchar(255),
    price int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into winter_shop.products
    (title, price)
values
    ('Milk', 90), ('Bread', 25), ('Cheese', 300);

create table winter_shop.users
(
    id bigserial primary key,
    username varchar(36) not null,
    password varchar(80) not null

);

create table winter_shop.roles
(
    id bigserial primary key,
    name_role varchar(50) not null
);

create table winter_shop.user_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into winter_shop.roles (name_role) values ('ROLE_USER'), ('ROLE_ADMIN');

insert into winter_shop.users (username, password)
values
    ('Bob', '$2a$12$vpmdxQ3M8lXB63SWHDkIMuwJYqIkqRZTrKozEZazguAEOTj/IzkcK'),
    ('John', '$2a$12$vpmdxQ3M8lXB63SWHDkIMuwJYqIkqRZTrKozEZazguAEOTj/IzkcK');

insert into winter_shop.user_roles (user_id, role_id) values (1,1), (2,2);