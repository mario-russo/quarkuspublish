package com.br.mariorusso.adapter.in.rest.perfil;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record PerfilDtoIn(
        String titulo,
        String sobre) {
}
