package com.br.mariorusso.interfaces.rest.login;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record LoginDtoOut(
    String email,
    String nome
) {


} 