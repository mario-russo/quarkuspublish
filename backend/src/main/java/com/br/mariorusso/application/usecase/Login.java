package com.br.mariorusso.application.usecase;

import com.br.mariorusso.application.auth.Roles;
import com.br.mariorusso.domain.model.Usuario;
import com.br.mariorusso.application.ports.in.LoginCore;
import com.br.mariorusso.adapter.out.persistence.entity.UsuarioEntity;

import com.br.mariorusso.adapter.in.rest.login.RegisterDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class Login implements LoginCore<UsuarioEntity> {

    @Inject
    UsuarioUseCase usuarioUseCase;

    @Override
    public UsuarioEntity login(String email, String senha) {
        UsuarioEntity usuario =  UsuarioEntity.find("email= ?1 AND senha= ?2", email,senha).firstResult();
        return usuario;
    }

    public  void register (RegisterDto dto){
        Usuario usuario = new Usuario();
        usuario.addRoles(Roles.USER);

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuarioUseCase.save(usuario);

    }
    
}
