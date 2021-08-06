INSERT INTO user (id, username, password, name, phone, email, ENABLED)
VALUES (1, 'cappuccino', 'cappuccino', 'cappuccino', '010-3333-3333', 'cappuccino@cappuccino.com', 1);
INSERT INTO authority (username, authority)
VALUES (1, 'ROLE_USER');

INSERT INTO user (id, username, password, name, phone, email, ENABLED)
VALUES (2, 'dummy', 'dummy', 'dummy', '010-3333-3333', 'dummy@dummy.com', 1);


INSERT INTO address (id, user_id, city, street, zipcode)
VALUES (1, 1, 'city1', 'street1' , 'zipcode1');

INSERT INTO address (id, user_id, city, street, zipcode)
VALUES (2, 1, 'city2', 'street2' , 'zipcode2');

INSERT INTO address (id, user_id, city, street, zipcode)
VALUES (3, 2, 'city3', 'street3' , 'zipcode3');
