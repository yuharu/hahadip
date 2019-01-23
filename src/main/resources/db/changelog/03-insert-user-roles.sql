--liquibase formatted sql
--changeset Yuriy.Kiselyov:01_insert_test_users
INSERT INTO user_role (id, name, role) VALUES
('e783e111-1686-47af-87bf-9be2db64cf89', 'Пользователь', 'ROLE_USER'),
('a383e111-1686-47af-87bf-9be2db64cf95', 'Администратор', 'ROLE_ADMIN');

INSERT INTO users_roles (username, role_id) VALUES
('potato', 'a383e111-1686-47af-87bf-9be2db64cf95');