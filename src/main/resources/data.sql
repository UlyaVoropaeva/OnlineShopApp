insert into users(id, login, password, authority) values('1','superadmin',  '12345', 'ROLE_SUPERADMIN');
insert into users(id, login, password, authority) values('2','admin',  '12345', 'ROLE_ADMIN');
insert into users(id, login, password, authority) values('3','MANAGER',  '12345', 'ROLE_MANAGER');
insert into users(id, login, password, authority) values('4','user',  '12345', 'ROLE_USER');

INSERT INTO categories (id, name) VALUES (1, 'Игрушки'), (2, 'Хлеб'), (3, 'Молочные продукты'), (4, 'Продукты');

INSERT INTO products (id, name, description) VALUES (1, 'PS5', 'Игровая приставка'),
                                                    (2, 'Сдобная булка', 'Хлебо-булочное изделие с завода №4'),
                                                    (3, 'Хлеб Бородинский', 'Хлебо-булочное изделие с завода №4'),
                                                    (4, 'Молоко 3%', 'Молочные продукты с фермы'),
                                                    (5, 'Молоко 1%', 'Молочные продукты с фермы'),
                                                    (6, 'Кефир Фермерский 5%', 'Молочные продукты с фермы');

INSERT INTO products_categories (productid, categoryid) VALUES (1,1),
                                                                   (2,2),
                                                                   (2,4),
                                                                   (3,2),
                                                                   (3,4),
                                                                   (4,3),
                                                                   (5,3),
                                                                   (6,3);
