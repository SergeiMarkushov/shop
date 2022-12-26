create table winter_shop.products
(
    id bigserial primary key,
    title varchar(255),
    category_id bigint references categories(id),
    price int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into winter_shop.categories (title) values ('Food'), ('Others');

insert into winter_shop.products
    (title, price, category_id)
values
    ('Milk', 90, 1), ('Bread', 25, 1), ('Cheese', 300, 1);

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

create table winter_shop.orders
(
    id bigserial primary key,
    user_id bigint not null references winter_shop.users(id),
    total_price int not null ,
    address  varchar(255),
    phone varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table winter_shop.order_items
(
    id bigserial primary key,
    product_id bigint not null references winter_shop.products(id),
    order_id bigint not null references winter_shop.orders(id),
    quantity int not null ,
    price_per_product int not null ,
    price int not null ,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table winter_shop.categories
(
    id bigserial primary key ,
    title varchar(255) unique ,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp

);