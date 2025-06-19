-- CUIDADO: Limpar tabelas (apaga dados, use só em ambiente de desenvolvimento!)
DELETE FROM usuarios_tem_perfis;
DELETE FROM usuarios;
DELETE FROM perfis;
DELETE FROM horas;

-- Inserir perfis iniciais
INSERT INTO perfis (id, descricao) VALUES
(1, 'ADMIN'),
(2, 'MEDICO'),
(3, 'PACIENTE');

-- Inserir horários disponíveis
INSERT INTO horas (id, hora_minuto) VALUES
(1, '07:00:00'),
(2, '07:30:00'),
(3, '08:00:00'),
(4, '08:30:00'),
(5, '09:00:00'),
(6, '09:30:00'),
(7, '10:00:00'),
(8, '10:30:00'),
(9, '11:00:00'),
(10, '11:30:00'),
(11, '13:00:00'),
(12, '13:30:00'),
(13, '14:00:00'),
(14, '14:30:00'),
(15, '15:00:00'),
(16, '15:30:00'),
(17, '16:00:00'),
(18, '16:30:00'),
(19, '17:00:00'),
(20, '17:30:00');

-- Inserir usuário administrador com senha bcrypt da senha "admin"
INSERT INTO usuarios (id, email, senha, ativo, codigo_verificador)
VALUES (
  1,
  'admin@clinica.com',
  '$2a$10$K9yT0vlz8Op7nFtLdCzZOe/Ee9sF0kYj93b7NyPny7YXj0QfIfGva', -- hash bcrypt da senha "admin"
  true,
  NULL
);

-- Associar usuário ao perfil ADMIN
INSERT INTO usuarios_tem_perfis (usuario_id, perfil_id) VALUES
(1, 1);