-- =========================
-- USUÁRIOS
-- =========================

INSERT INTO usuario (nome, email, cpf, senha, tipo_usuario)
VALUES
('Carlos Silva',
 'carlos@email.com',
 '101111111111',
 '$2a$10$7q7uE5lKx9Y8L6vYpQeMHeq1V3E7nU1gT9QKcJzXQkGQm7dK9KzOe',
 'COMUM');

INSERT INTO usuario (nome, email, cpf, senha, tipo_usuario)
VALUES
('Ana Souza',
 'ana@email.com',
 '22222222222',
 '$2a$10$7q7uE5lKx9Y8L6vYpQeMHeq1V3E7nU1gT9QKcJzXQkGQm7dK9KzOe',
 'COMUM');

INSERT INTO usuario (nome, email, cpf, senha, tipo_usuario)
VALUES
('Loja Exemplo',
 'loja@email.com',
 '33333333333',
 '$2a$10$7q7uE5lKx9Y8L6vYpQeMHeq1V3E7nU1gT9QKcJzXQkGQm7dK9KzOe',
 'LOJISTA');

-- =========================
-- CARTEIRAS
-- =========================

INSERT INTO carteira (saldo, usuario_id)
VALUES
(1000.00, 1);

INSERT INTO carteira (saldo, usuario_id)
VALUES
(2000.00, 2);

INSERT INTO carteira (saldo, usuario_id)
VALUES
(5000.00, 3);
