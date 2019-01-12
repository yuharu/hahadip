--liquibase formatted sql
--changeset Yuriy.Kiselyov:01_insert_test_users
INSERT INTO user_credential (username, password) VALUES
('potato', '$2a$10$7IBEDjWUmSU3d.jlAxUwEuCBr6mRF9p7mAIRuO1s9ySNLLcRaJ3Ti'), -- password 123
('aligato', '$2a$10$ruCrvgMczzDpRUAkFICEHO/TcHb5DPbMIZqUiWmDkbx7QRHh4lHj2'); -- password 456
