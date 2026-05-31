create table if not exists perfil (
    id bigint generated always as identity primary key,
    titulo varchar(255),
    sobre varchar(1200),
    usuario_id bigint not null unique,

    constraint fk_perfil_usuario foreign key (usuario_id)
        references usuarios(id)
    );