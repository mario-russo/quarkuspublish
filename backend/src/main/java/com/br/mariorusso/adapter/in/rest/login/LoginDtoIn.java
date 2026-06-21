package com.br.mariorusso.adapter.in.rest.login;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record LoginDtoIn(
    String email,
    String senha
) {
    
}
