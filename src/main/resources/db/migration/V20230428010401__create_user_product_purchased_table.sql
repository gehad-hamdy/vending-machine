create table buyer_products_purchased (
             id int8 generated by default as identity,
             user_id int8 not null,
             product_id int8 not null,
             quantity integer,
             price double precision,
             created_at timestamp,
             updated_at timestamp,
             primary key (id)
);

alter table if exists buyer_products_purchased
    add constraint FK_buyer_products_purchased_user
    foreign key (user_id)
    references users;

alter table if exists buyer_products_purchased
    add constraint FK_buyer_products_purchased_products
    foreign key (product_id)
    references products;