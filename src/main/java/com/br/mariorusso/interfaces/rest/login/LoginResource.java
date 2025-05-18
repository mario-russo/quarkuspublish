package com.br.mariorusso.interfaces.rest.login;

import com.br.mariorusso.core.service.LoginCore;
import com.br.mariorusso.infra.entity.UsuarioEntity;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    @Inject
    LoginCore<UsuarioEntity> loginUsuario;
    
    @POST
    public Response login(LoginDtoIn login){
        UsuarioEntity login2 = loginUsuario.login(login.senha(), login.email());
        return Response.ok( new LoginDtoOut(login2.email, login2.nome)).build();
    }
}
