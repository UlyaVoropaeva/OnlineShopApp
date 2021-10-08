create table if not exists PRODUCTS
(
    ID BIGINT auto_increment primary key,
    NAME VARCHAR (255),
    description VARCHAR (255),
    price decimal

);

create table if not exists USERS
(
    ID BIGINT auto_increment primary key,
    login VARCHAR (50) not null,
    password VARCHAR (50) not null,
    authority VARCHAR (50) not null
);



create table if not exists categories
(
    ID BIGINT auto_increment primary key,
    name VARCHAR (255)

);

create table if not exists products_categories
(
    productID BIGINT,
    categoryID BIGINT,
    constraint TABLE_PRODUCKS_ID_FK foreign key
        (productID) references PRODUCTS (ID),
    constraint TABLE_categorys_ID_FK foreign key
        (categoryID) references categories (ID )

);
create table if not exists carts
(
    ID BIGINT auto_increment primary key,
    countProduct BIGINT,
    productID BIGINT,
    constraint TABLE_PRODUCTS_ID_FK foreign key
        (productID) references PRODUCTS (ID)

);
