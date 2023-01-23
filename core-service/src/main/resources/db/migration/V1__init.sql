create table winter_shop.products
(
    id bigserial primary key,
    title varchar(255),
    category_id bigint references categories(id),
    price numeric(8, 2) not null ,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into winter_shop.categories (title) values ('Food'), ('Others');

insert into winter_shop.products
    (title, price, category_id)
values
    ('Milk', 90.10, 1), ('Bread', 25.10, 1), ('Cheese', 300.10, 1);


create table winter_shop.orders
(
    id bigserial primary key,
    username varchar(255) not null,
    total_price numeric(8, 2) not null ,
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
    price_per_product numeric(8, 2) not null ,
    price numeric(8, 2) not null ,
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