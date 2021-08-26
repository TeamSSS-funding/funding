INSERT INTO item (id, project_id, title) VALUES (1, 1, '병뚜껑');
INSERT INTO item (id, project_id, title) VALUES (2, 1, '텀블러');

INSERT INTO reward (id, project_id, title, description, amount)
VALUES (1, 1, '병뚜껑', '병뚜껑', 10000);
INSERT INTO reward_item (id, reward_id, item_id, quantity) VALUES (1, 1, 1, 1);

INSERT INTO reward (id, project_id, title, description, amount)
VALUES (2, 1, '텀블러', '텀블러', 30000);
INSERT INTO reward_item (id, reward_id, item_id, quantity) VALUES (2, 2, 2, 1);

INSERT INTO reward (id, project_id, title, description, amount)
VALUES (3, 1, '병뚜껑과 텀블러 세트', '병뚜껑 x 2 텀블러 x1', 50000);
INSERT INTO reward_item (id, reward_id, item_id, quantity) VALUES (3, 3, 1, 2);
INSERT INTO reward_item (id, reward_id, item_id, quantity) VALUES (4, 3, 2, 1);