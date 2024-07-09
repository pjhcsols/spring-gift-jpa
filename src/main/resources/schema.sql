drop table if exists products;

-- for Product
create table products (
    id          bigint generated by default as identity,
    name        varchar(15),
    price       bigint,
    image_url   varchar(255),
    primary key (id)
);

-- for User
create table users (
    id          bigint generated by default as identity,
    email       varchar(64) not null unique,
    password    varchar(64), -- store hashed pw
    permission  varchar(64),
    primary key (id)
);

create index users_email_idx ON users (email);

-- for wish
create table wishes (
    id          bigint generated by default as identity,
    product_id  bigint not null,
    user_id     bigint not null,
    quantity    bigint,
    primary key (id),
    foreign key (product_id) references products(id),
    foreign key (user_id) references users(id)
);

create index wishes_user_idx ON wishes (user_id);
create index wishes_product_user_idx ON wishes (product_id, user_id);