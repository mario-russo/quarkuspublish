insert into usuarios (nome, email, senha, roles)
values
    ('Mario Russo', 'mario@gmail.com', '123456', ARRAY[0,1]::smallint[]),
    ('User', 'user@gmail.com', '123456', ARRAY[0]::smallint[]),
    ('Admin', 'admin@gmail.com', '123456', ARRAY[1]::smallint[]);

insert into publicacao
(conteudo, data_publicacao, usuario_id)
values
    ('Conteudo Mario', now(), 1),
    ('Conteudo User', now(), 2),
    ('Conteudo Admin', now(), 3);

insert into comentario
(conteudo, data_comentario, usuario_id, publicacao_id)
values
    ('Comentario Mario', now(), 1, 1),
    ('Comentario User', now(), 2, 2),
    ('Comentario Admin', now(), 3, 3);

insert into curtida
(criado_em, usuario_id, publicacao_id)
values
    (now(), 1, 1),
    (now(), 2, 2),
    (now(), 3, 3);

insert into perfil
(titulo, sobre, usuario_id)
values
    ('Backend Java', 'Perfil Mario', 1),
    ('Frontend Vue', 'Perfil User', 2),
    ('Administrador', 'Perfil Admin', 3);

ALTER SEQUENCE usuarios_id_seq RESTART WITH 4;
ALTER SEQUENCE publicacao_id_seq RESTART WITH 4;
ALTER SEQUENCE comentario_id_seq RESTART WITH 4;
ALTER SEQUENCE curtida_id_seq RESTART WITH 4;
ALTER SEQUENCE perfil_id_seq RESTART WITH 4;