package com.br.mariorusso.interfaces.rest.usuario;

import com.br.mariorusso.core.model.Usuario;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record UsuarioDtoOut(
        Long id ,
    String nome,
    String email
) {

    public static UsuarioDtoOut dtoOut (Usuario usuario){
        return new UsuarioDtoOut(usuario.getId(),usuario.getNome(), usuario.getEmail());
    }
} 