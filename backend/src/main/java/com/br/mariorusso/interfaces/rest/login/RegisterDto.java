package com.br.mariorusso.interfaces.rest.login;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record RegisterDto(
        String nome,
        String email,
        String senha
) {
}
