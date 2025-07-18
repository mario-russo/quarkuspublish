package com.br.mariorusso.interfaces.rest.login;

import com.br.mariorusso.application.Login;
import com.br.mariorusso.auth.JwtService;
import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.service.LoginCore;
import com.br.mariorusso.infra.entity.UsuarioEntity;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.Map;


@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    final LoginCore<UsuarioEntity> loginUsuario;
    final JwtService jwtService;

    @Inject
    public LoginResource(LoginCore<UsuarioEntity> loginUsuario, JwtService jwt) {
        this.loginUsuario = loginUsuario;
        this.jwtService = jwt;
    }

    @POST
    @Path("/login")
    public Response login(LoginDtoIn login){
        try{
            UsuarioEntity usuario = loginUsuario.login(login.email(), login.senha());
            Usuario user = usuario.toDomain();


            String token = jwtService.generateToken(user);
            return Response.ok(Map.of("token",token)).build();

        } catch (Exception e) {
            return  Response.status(Response.Status.NOT_FOUND).entity(Map.of("Erro","Usuário não encontrado!!!")).build();
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
