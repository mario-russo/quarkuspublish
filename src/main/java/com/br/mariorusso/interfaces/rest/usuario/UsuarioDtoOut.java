package com.br.mariorusso.interfaces.rest.usuario;

import com.br.mariorusso.core.model.Usuario;

public record UsuarioDtoOut(
    String nome,
    String email
) {

    public static UsuarioDtoOut dtoOut (Usuario usuario){
        return new UsuarioDtoOut(usuario.getNome(), usuario.getEmail());
    }
} 