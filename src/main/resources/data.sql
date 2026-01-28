-- LIMPA AS TABELAS (ordem correta por causa das FK)
DELETE FROM transacao;
DELETE FROM carteira;
DELETE FROM usuario;

-- =========================
-- USUÁRIOS
-- =========================
INSERT INTO usuario (id, nome_completo, email, cpf_cnpj, senha, tipo_usuario) VALUES
(1, 'Carlos Silva', 'carlos@email.com', '101111111111',
 '$2a$10$7q7uE5lKx9Y8L6vYpQeMHeq1V3E7nU1gT9QKcJzXQkGQm7dK9KzOe',
 'COMUM'),

(2, 'Ana Souza', 'ana@email.com', '22222222222',
 '$2a$10$7q7uE5lKx9Y8L6vYpQeMHeq1V3E7nU1gT9QKcJzXQkGQm7dK9KzOe',
 'COMUM'),

(3, 'Loja Exemplo', 'loja@email.com', '33333333333',
 '$2a$10$7q7uE5lKx9Y8L6vYpQeMHeq1V3E7nU1gT9QKcJzXQkGQm7dK9KzOe',
 'LOJISTA');

-- =========================
-- CARTEIRAS
-- =========================
INSERT INTO carteira (id, saldo, usuario_id) VALUES
(1, 1000.00, 1),
(2, 2000.00, 2),
(3, 5000.00, 3);

-- =========================
-- TRANSAÇÕES (opcional, exemplo)
-- =========================
INSERT INTO transacao (id, valor, data_hora_transacao, pagador_id, recebedor_id) VALUES
(1, 100.00, now(), 1, 2);
