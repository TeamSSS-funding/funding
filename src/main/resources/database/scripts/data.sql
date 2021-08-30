INSERT INTO category (id, name) VALUES (1, '영화');
INSERT INTO category (id, name) VALUES (2, '음악');
INSERT INTO category (id, name) VALUES (3, '게임');
INSERT INTO category (id, name) VALUES (4, '소설');
INSERT INTO category (id, name) VALUES (5, '패션');
INSERT INTO category (id, name) VALUES (6, '제품 디자인');

INSERT INTO user (id, username, password, name, phone, email, ENABLED)
VALUES (1, 'hello', '$2a$10$Dqae95qzufCYwwJp.Gk3YuXji4jdakPuk5mJBujkgucwK0oStSxAa', '안녕', '010-8648-4442', 'hello@mygoodsupporter.com', 1);

INSERT INTO project (id, user_id, category_id, title, subtitle, target_amount, current_amount, status)
VALUES (1, 1, 6, '뚜웅장, 웅크린선인장 특대형 인형', '뚜웅장..!!', 6000000, 0, 'FUNDING');

INSERT INTO item (id, project_id, title) VALUES (1, 1, '웅크린선인장 피규어 키링');
INSERT INTO item (id, project_id, title) VALUES (2, 1, '웅크린선인장 관절인형(화분포함)');
INSERT INTO item (id, project_id, title) VALUES (3, 1, '웅크린선인장 엽서');
INSERT INTO item (id, project_id, title) VALUES (4, 1, '뚜웅장인형');

INSERT INTO reward (id, project_id, title, description, amount) VALUES (1, 1, '선물 없이 후원하기', '그냥 후원하세요', 1000);
INSERT INTO reward (id, project_id, title, description, amount) VALUES (2, 1, '키링', '그냥 후원하세요', 16000);
INSERT INTO reward (id, project_id, title, description, amount) VALUES (3, 1, '키링키링', '그냥 후원하세요', 24000);

INSERT INTO reward_item (id, reward_id, item_id, quantity) VALUES (1, 2, 1, 1);
INSERT INTO reward_item (id, reward_id, item_id, quantity) VALUES (2, 3, 1, 2);
INSERT INTO reward_item (id, reward_id, item_id, quantity) VALUES (3, 3, 4, 1);