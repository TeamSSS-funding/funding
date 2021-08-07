INSERT INTO user (id, username, password, name, phone, email, ENABLED)
VALUES (1, 'mocha', 'mocha', 'mocha', '010-3333-3333', 'mocha@mygoodsupporter.github.io', 1);

INSERT INTO category (id, name) VALUES (1, '영화');
INSERT INTO category (id, name) VALUES (2, '음악');
INSERT INTO category (id, name) VALUES (3, '게임');

INSERT INTO project (id, user_id, category_id, title, subtitle, goal_amount, current_amount, status, start_date, end_date)
VALUES (1, 1, 1, 'title', 'subtitle', 0, 0, 'PREPARING', null, null);

INSERT INTO project (id, user_id, category_id, title, subtitle, goal_amount, current_amount, status, start_date, end_date)
VALUES (2, 1, 1, 'title2', 'subtitle2', 0, 0, 'PREPARING', null, null);