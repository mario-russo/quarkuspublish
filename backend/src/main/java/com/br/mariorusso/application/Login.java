package com.br.mariorusso.application;

import com.br.mariorusso.core.service.LoginCore;
import com.br.mariorusso.infra.entity.UsuarioEntity;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class Login implements LoginCore<UsuarioEntity> {


    @Override
    public UsuarioEntity login(String senha, String email) {
        UsuarioEntity usuario =  UsuarioEntity.find("email= ?1 AND senha= ?2", email,senha).firstResult();
        return usuario;
    }
    
}
