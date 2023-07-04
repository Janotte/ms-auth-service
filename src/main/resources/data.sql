INSERT INTO tb_permissions (permission_id, name, description) VALUES
(UUID_TO_BIN('5f477c0f-19f4-11ee-a34f-8cb0e92fd151'),'USER_READ','Allows you to consult your own user account'),
(UUID_TO_BIN('5f481e08-19f4-11ee-a34f-8cb0e92fd151'),'USER_WRITE','Allows you to update your own user account'),
(UUID_TO_BIN('5f481fc9-19f4-11ee-a34f-8cb0e92fd151'),'USERS_READ','Allows querying users, groups and permissions'),
(UUID_TO_BIN('5f482074-19f4-11ee-a34f-8cb0e92fd151'),'USERS_WRITE','Lets you create or edit users, groups, and permissions');

INSERT INTO tb_roles(role_id, name) VALUES
(UUID_TO_BIN('dd835c4a-19f4-11ee-a34f-8cb0e92fd151'), 'ADMIN'),
(UUID_TO_BIN('dd841b80-19f4-11ee-a34f-8cb0e92fd151'), 'CLIENT');

INSERT INTO tb_roles_permissions(role_id, permission_id) VALUES
(UUID_TO_BIN('dd835c4a-19f4-11ee-a34f-8cb0e92fd151'), UUID_TO_BIN('5f477c0f-19f4-11ee-a34f-8cb0e92fd151')),
(UUID_TO_BIN('dd835c4a-19f4-11ee-a34f-8cb0e92fd151'), UUID_TO_BIN('5f481e08-19f4-11ee-a34f-8cb0e92fd151')),
(UUID_TO_BIN('dd835c4a-19f4-11ee-a34f-8cb0e92fd151'), UUID_TO_BIN('5f481fc9-19f4-11ee-a34f-8cb0e92fd151')),
(UUID_TO_BIN('dd835c4a-19f4-11ee-a34f-8cb0e92fd151'), UUID_TO_BIN('5f482074-19f4-11ee-a34f-8cb0e92fd151')),
(UUID_TO_BIN('dd841b80-19f4-11ee-a34f-8cb0e92fd151'), UUID_TO_BIN('5f477c0f-19f4-11ee-a34f-8cb0e92fd151')),
(UUID_TO_BIN('dd841b80-19f4-11ee-a34f-8cb0e92fd151'), UUID_TO_BIN('5f481e08-19f4-11ee-a34f-8cb0e92fd151'));

INSERT INTO tb_users(user_id, username, full_name, email, password, cell_phone_number, image_url) VALUES
(UUID_TO_BIN('38b8f33c-19f8-11ee-a34f-8cb0e92fd151'), 'Administrator', 'System Administrator', 'admin@supptech.net.br', '12345678', '(0xx47) 99999-9999', '\images\v'),
(UUID_TO_BIN('38b9035c-19f8-11ee-a34f-8cb0e92fd151'), 'Client01', 'Client 01', 'client01@supptech.net.br', '12345678', '(0xx47) 99999-0001', '\images\01'),
(UUID_TO_BIN('38b904a2-19f8-11ee-a34f-8cb0e92fd151'), 'Client02', 'Client 02', 'client02@supptech.net.br', '12345678', '(0xx47) 99999-0002', '\images\02'),
(UUID_TO_BIN('38b90534-19f8-11ee-a34f-8cb0e92fd151'), 'Client03', 'Client 03', 'client03@supptech.net.br', '12345678', '(0xx47) 99999-0003', '\images\03'),
(UUID_TO_BIN('38b905bf-19f8-11ee-a34f-8cb0e92fd151'), 'Client04', 'Client 04', 'client04@supptech.net.br', '12345678', '(0xx47) 99999-0004', '\images\04'),
(UUID_TO_BIN('38b90641-19f8-11ee-a34f-8cb0e92fd151'), 'Client05', 'Client 05', 'client05@supptech.net.br', '12345678', '(0xx47) 99999-0005', '\images\05');

INSERT INTO tb_users_roles(user_id, role_id) VALUES
(UUID_TO_BIN('38b8f33c-19f8-11ee-a34f-8cb0e92fd151'), UUID_TO_BIN('dd835c4a-19f4-11ee-a34f-8cb0e92fd151')),
(UUID_TO_BIN('38b9035c-19f8-11ee-a34f-8cb0e92fd151'), UUID_TO_BIN('dd841b80-19f4-11ee-a34f-8cb0e92fd151')),
(UUID_TO_BIN('38b904a2-19f8-11ee-a34f-8cb0e92fd151'), UUID_TO_BIN('dd841b80-19f4-11ee-a34f-8cb0e92fd151')),
(UUID_TO_BIN('38b90534-19f8-11ee-a34f-8cb0e92fd151'), UUID_TO_BIN('dd841b80-19f4-11ee-a34f-8cb0e92fd151')),
(UUID_TO_BIN('38b905bf-19f8-11ee-a34f-8cb0e92fd151'), UUID_TO_BIN('dd841b80-19f4-11ee-a34f-8cb0e92fd151')),
(UUID_TO_BIN('38b90641-19f8-11ee-a34f-8cb0e92fd151'), UUID_TO_BIN('dd841b80-19f4-11ee-a34f-8cb0e92fd151'));