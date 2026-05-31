package com.br.mariorusso.interfaces.rest.perfil;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record PerfilDtoIn(
        String titulo,
        String sobre) {
}
