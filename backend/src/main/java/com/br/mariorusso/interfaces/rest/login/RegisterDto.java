package com.br.mariorusso.interfaces.rest.login;

public record RegisterDto(
        String nome,
        String email,
        String senha
) {
}
