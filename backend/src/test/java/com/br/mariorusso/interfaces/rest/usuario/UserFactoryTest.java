package com.br.mariorusso.interfaces.rest.usuario;

import com.br.mariorusso.application.Login;
import com.br.mariorusso.auth.Roles;
import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.infra.entity.UsuarioEntity;

import java.util.EnumSet;

import java.util.Set;

public class UserFactoryTest {

    public static final String NOME = "mario russo";
    public static final String EMAIL = "mario@gmail.com";
    public static final String PASSWORD = "123456";
    public static final Roles ROLES = Roles.USER;

    public static Usuario usuario() {
        Usuario usuario = new Usuario(null, NOME, EMAIL, PASSWORD);
        usuario.setRoles(ROLES);
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
