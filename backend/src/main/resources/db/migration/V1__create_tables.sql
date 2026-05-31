create table if not exists usuarios (
    id bigint generated always as identity primary key,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(100) not null,
    roles smallint[] not null
);

create table if not exists publicacao (
    id bigint generated always as identity primary key,
    conteudo varchar(255) not null,
    data_publicacao timestamp not null,
    usuario_id bigint not null,

    constraint fk_publicacao_usuario
        foreign key (usuario_id)
            references usuarios(id)
);

create table if not exists comentario (
    id bigint generated always as identity primary key,
    conteudo varchar(255) not null,
    data_comentario timestamp not null,
    publicacao_id bigint not null,
    usuario_id bigint not null,

    constraint fk_comentario_publicacao
        foreign key (publicacao_id)
            references publicacao(id),

    constraint fk_comentario_usuario
        foreign key (usuario_id)
            references usuarios(id)
);

create table if not exists curtida (
     id bigint generated always as identity primary key,
     criado_em timestamp not null,
     publicacao_id bigint not null,
     usuario_id bigint not null,

     constraint fk_curtida_publicacao
         foreign key (publicacao_id)
             references publicacao(id),

     constraint fk_curtida_usuario
         foreign key (usuario_id)
             references usuarios(id),

     constraint uk_usuario_publicacao
         unique (usuario_id, publicacao_id)
);

create table if not exists perfil (
    id bigint generated always as identity primary key,
    titulo varchar(255),
    sobre  varchar(1200),
    usuario_id bigint not null unique,
    constraint fk_perfil_usuario
        foreign key (usuario_id) references usuarios(id)

)