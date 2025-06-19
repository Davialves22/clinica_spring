-- Limpar tabelas (cuidado em produção)
DELETE FROM usuarios_tem_perfis;
DELETE FROM medicos;
DELETE FROM pacientes;
DELETE FROM usuarios;
DELETE FROM perfis;
DELETE FROM horas;

-- Perfis iniciais
INSERT INTO perfis (id, descricao) VALUES
(1, 'ADMIN'),
(2, 'MEDICO'),
(3, 'PACIENTE');

-- Horários disponíveis
INSERT INTO horas (id, hora_minuto) VALUES
(1, '07:00:00'), (2, '07:30:00'), (3, '08:00:00'), (4, '08:30:00'),
(5, '09:00:00'), (6, '09:30:00'), (7, '10:00:00'), (8, '10:30:00'),
(9, '11:00:00'), (10, '11:30:00'), (11, '13:00:00'), (12, '13:30:00'),
(13, '14:00:00'), (14, '14:30:00'), (15, '15:00:00'), (16, '15:30:00'),
(17, '16:00:00'), (18, '16:30:00'), (19, '17:00:00'), (20, '17:30:00');

-- Usuários (senha bcrypt gerada para cada senha)
INSERT INTO usuarios (id, email, senha, ativo, codigo_verificador) VALUES
(1, 'admin@clinica.com', '$2a$10$K9yT0vlz8Op7nFtLdCzZOe/Ee9sF0kYj93b7NyPny7YXj0QfIfGva', true, NULL),    -- senha: admin
(2, 'medico@clinica.com', '$2a$10$x2wgrZnWqhD7On9zOQeouOk8djGzGwlx8k9uMrqO0Dxzt9Cu9jq6S', true, NULL),   -- senha: medico1
(3, 'paciente@clinica.com', '$2a$10$cJ3KPM1TQ36scwq9lE6O6OcpFPLXNlhBNWJ8vQiWqtiZmt6iEclE6', true, NULL); -- senha: paciente1

-- Associar perfis
INSERT INTO usuarios_tem_perfis (usuario_id, perfil_id) VALUES
(1, 1), -- ADMIN
(2, 2), -- MÉDICO
(3, 3); -- PACIENTE

-- Dados médicos
INSERT INTO medicos (id, nome, crm, data_inscricao, id_usuario) VALUES
(1, 'Dr. João Silva', 123456, '2020-01-15', 2);

-- Dados pacientes
INSERT INTO pacientes (id, nome, data_nascimento, id_usuario) VALUES
(1, 'Maria Oliveira', '1990-05-20', 3);