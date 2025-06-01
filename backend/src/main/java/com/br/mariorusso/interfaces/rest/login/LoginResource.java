package com.br.mariorusso.interfaces.rest.login;

import com.br.mariorusso.application.Login;
import com.br.mariorusso.core.service.LoginCore;
import com.br.mariorusso.infra.entity.UsuarioEntity;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {


    final LoginCore<UsuarioEntity> loginUsuario;

    @Inject
    public LoginResource(LoginCore<UsuarioEntity> loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    @POST
    public Response login(LoginDtoIn login){
        try{
            UsuarioEntity login2 = loginUsuario.login(login.senha(), login.email());
            return Response.ok( new LoginDtoOut(login2.email, login2.nome)).build();

        } catch (Exception e) {
            return  Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado").build();
        }


    }
    @Path("/register")
    @POST()
    public  Response register (RegisterDto dto){
        Login login = (Login) loginUsuario;
        login.register(dto);
        return  Response.ok("Usuário salvo com sucesso!!").build();
    }
}
