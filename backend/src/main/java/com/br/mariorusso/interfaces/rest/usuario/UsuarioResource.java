package com.br.mariorusso.interfaces.rest.usuario;

import java.util.List;

import com.br.mariorusso.application.UsuarioUseCase;
import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.service.ServiceCore;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.ClaimValue;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    @Claim("id")
    ClaimValue<Long> id;

    final private ServiceCore<Usuario> service;

    @Inject
    public UsuarioResource(UsuarioUseCase service) {
        this.service = service;
    }

    @POST
    @RolesAllowed({"ADMIN"})
    public Response salvaUsuario(UsuarioDtos dto) {
        service.save(new Usuario(null, dto.nome(), dto.email(), dto.senha()));
        return Response.ok("Usuario salvo com sucesso").build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {

            Usuario usuario = service.findById(id);
            UsuarioDtoOut dto = new UsuarioDtoOut(usuario.getNome(), usuario.getEmail());
            return Response.ok(dto).build();

        } catch (Exception e) {
            return Response.status(404).build();
        }
    }


    @PATCH
    @RolesAllowed("USER")
    public Response atualizarUsuario(UsuarioDtos usuario) {

        Usuario user = service.findById(id.getValue());

        user.setEmail(usuario.email());
        user.setNome(usuario.nome());
        user.setSenha(usuario.senha());


        service.update(user);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    @Transactional
    public Response deletarUsuario(@PathParam("id") Long id) {
        try{

            Usuario usuarioId = service.findById(id);
            service.delete(usuarioId);
            return Response.ok("Usuario deletado com sucesso").build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado").build();
        }

    }

    @GET
    @RolesAllowed("ADMIN")
    public Response listarUsuarios() {

        List<Usuario> all = service.findAll();
        List<UsuarioDtoOut> out = all.stream().map(UsuarioDtoOut::dtoOut).toList();
        return Response.ok(out).build();
    }

}
