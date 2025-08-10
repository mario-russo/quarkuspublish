-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;
-- Tabela de usuários
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    roles SMALLINT []
);

-- Tabela de publicações
CREATE TABLE IF NOT EXISTS publicacao (
    id SERIAL PRIMARY KEY,
    conteudo TEXT NOT NULL,
    dataPublicacao TIMESTAMP NOT NULL,
    usuario_id INTEGER,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Tabela de comentários
CREATE TABLE IF NOT EXISTS comentario (
    id SERIAL PRIMARY KEY,
    conteudo TEXT NOT NULL,
    dataComentario TIMESTAMP NOT NULL,
    usuario_id INTEGER NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
    publicacao_id INTEGER NOT NULL REFERENCES publicacao(id) ON DELETE CASCADE
);

-- Tabela de curtidas
CREATE TABLE IF NOT EXISTS curtida (
    id SERIAL PRIMARY KEY,
    dataLike TIMESTAMP NOT NULL,
    usuario_id INTEGER NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
    publicacao_id INTEGER NOT NULL REFERENCES publicacao(id) ON DELETE CASCADE
);

-- Inserir usuário
INSERT INTO usuarios (nome, email, senha, roles)
VALUES ('Mario Russo', 'mario@gmail.com', '123456', ARRAY[0,1]);

INSERT INTO usuarios (nome, email, senha, roles)
VALUES ('user', 'user@gmail.com', '123456', ARRAY[0]);

INSERT INTO usuarios (nome, email, senha, roles)
VALUES ('admin', 'admin@gmail.com', '123456', ARRAY[1]);

-- Inserir publicação associada ao usuário 1
INSERT INTO publicacao ( conteudo, dataPublicacao, usuario_id)
VALUES ('Conteúdo inicial', NOW(), 1);

INSERT INTO publicacao ( conteudo, dataPublicacao, usuario_id)
VALUES ('conteudo user', NOW(), 2);


INSERT INTO publicacao ( conteudo, dataPublicacao, usuario_id)
VALUES ('conteudo admin', NOW(), 3);


-- Inserir comentário associado à publicação 1 e usuário 1
INSERT INTO comentario (conteudo, dataComentario, usuario_id, publicacao_id)
VALUES ('Comentário inicial', NOW(), 1, 1);

-- Inserir curtida associada à publicação 1 e usuário 1
INSERT INTO curtida (dataLike, usuario_id, publicacao_id)
VALUES (NOW(), 1, 1);
