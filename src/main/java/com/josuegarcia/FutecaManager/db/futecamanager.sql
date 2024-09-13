-- drop database futecamanagerin5bv;
use futecamanagerin5bv;

-- Inserción de campos de fútbol
INSERT INTO soccer_field (name, type, capacity, photo) VALUES
('Campo Central', 'Natural', '500', 'campo_central.jpg'),
('Campo Norte', 'Sintético', '300', 'campo_norte.jpg'),
('Campo Sur', 'Natural', '400', 'campo_sur.jpg'),
('Campo Este', 'Sintético', '200', 'campo_este.jpg'),
('Campo Oeste', 'Natural', '350', 'campo_oeste.jpg');


-- Inserción de usuarios
INSERT INTO user (name, surname, username, email, password) VALUES
('Juan', 'Pérez', 'juanp', 'juan.perez@example.com', 'password123'),
('Ana', 'García', 'anag', 'ana.garcia@example.com', 'password456'),
('Luis', 'Martínez', 'luism', 'luis.martinez@example.com', 'password789'),
('María', 'López', 'marial', 'maria.lopez@example.com', 'password321'),
('Carlos', 'Ramírez', 'carlosr', 'carlos.ramirez@example.com', 'password654');

select * from user;
-- Inserción de reservas
INSERT INTO reservation (start, end, payment, status, user_id, soccer_field_id) VALUES
('2024-09-15 08:00:00', '2024-09-15 10:00:00', 'PROCESS', 'PROCESS', 1, 1),
('2024-09-16 09:00:00', '2024-09-16 11:00:00', 'PROCESS', 'PROCESS', 2, 2),
('2024-09-17 10:00:00', '2024-09-17 12:00:00', 'PROCESS', 'PROCESS', 3, 3),
('2024-09-18 11:00:00', '2024-09-18 13:00:00', 'PROCESS', 'PROCESS', 4, 4),
('2024-09-19 12:00:00', '2024-09-19 14:00:00', 'PROCESS', 'PROCESS', 5, 5);

select * from reservation;
