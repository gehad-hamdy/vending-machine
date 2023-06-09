create table products (
           id int8 generated by default as identity,
           name varchar(255) not null,
           cost double precision,
           amount_available integer default 0,
           user_id int8 not null,
           created_at timestamp,
           updated_at timestamp,
           primary key (id)
);

alter table if exists products
    add constraint FK_product_user
    foreign key (user_id)
    references users;