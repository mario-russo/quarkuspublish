package com.br.mariorusso.interfaces.rest.usuario;

import com.br.mariorusso.application.usecase.Login;
import com.br.mariorusso.application.auth.Roles;
import com.br.mariorusso.domain.model.Usuario;
import com.br.mariorusso.adapter.out.persistence.entity.UsuarioEntity;


public class UserFactoryTest {

    public static final String NOME = "Mario Russo";
    public static final String EMAIL = "russo@gmail.com";
    public static final String PASSWORD = "123456";
    public static final Roles ROLES = Roles.USER;

    public static Usuario usuario() {
        Usuario usuario = new Usuario(null, NOME, EMAIL, PASSWORD);
        usuario.addRoles(ROLES);
        return usuario;
    }
    public static Usuario loginUser(){
        UsuarioEntity user = new Login().login(EMAIL,PASSWORD);
        return user.toDomain();
    }
    public static Usuario loginUser(String email, String password){
        UsuarioEntity user = new Login().login(email,password);
        return  user.toDomain();
    }

}
