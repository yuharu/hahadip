--liquibase formatted sql
--changeset Irina.Druzhkova:02_insert_test_positions
INSERT INTO position (id, code, name) VALUES
(1, 'junior', 'Младший программист'),
(2, 'middle', 'Программист'),
(3, 'senior', 'Старший программист'),
(4, 'tech_director', 'Технический директор'),
(5, 'director', 'Директор'),
(6, 'admin', 'Администратор');
