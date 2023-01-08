USE `Rbac0WithGroupHierarchy`;

INSERT INTO role (role) VALUES ('admin');
INSERT INTO role (role) VALUES ('test1');
INSERT INTO role (role) VALUES ('test2');
INSERT INTO role (role) VALUES ('test3');
INSERT INTO role (role) VALUES ('test4');


INSERT INTO user (username, password) VALUES ('admin', 'admin123');
INSERT INTO user (username, password) VALUES ('testUser1', 'test');
INSERT INTO user (username, password) VALUES ('testUser2', 'test');

INSERT INTO role_user (role, username) VALUES ('admin', 'admin');
INSERT INTO role_user (role, username) VALUES ('admin', 'testUser1');
INSERT INTO role_user (role, username) VALUES ('test1', 'testUser2');

INSERT INTO permission (id, expression) VALUES (1, 'system:role:create');


INSERT INTO user_group (id, text) VALUES (100, '全国总部');
INSERT INTO user_group (id, text) VALUES (101, '省级分行');
INSERT INTO user_group (id, text) VALUES (102, '市级分行');



INSERT INTO user_group_hierarchy (user_group_id, node_name, path_enum) VALUES (100, 'A', 'A');
INSERT INTO user_group_hierarchy (user_group_id, node_name, path_enum) VALUES (101, 'B', 'AB');
INSERT INTO user_group_hierarchy (user_group_id, node_name, path_enum) VALUES (102, 'C', 'ABC');




