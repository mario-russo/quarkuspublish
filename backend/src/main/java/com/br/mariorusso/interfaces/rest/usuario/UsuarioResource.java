package com.br.mariorusso.interfaces.rest.usuario;

import java.util.List;

import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.service.ServiceCore;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    ServiceCore<Usuario> service;

    @POST
    public Response salvaUsuario(UsuarioDtos dto) {
        service.save(new Usuario(null, dto.nome(), dto.email(), dto.senha()));
        return Response.ok("Usuario salvo com sucesso").build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Usuario usuario = service.findById(id);
        UsuarioDtoOut dto = new UsuarioDtoOut(usuario.getNome(), usuario.getEmail());
        return Response.ok(dto).build();
    }

    @Path("/atualiza")
    public Response atualizarUsuario(Usuario usuario) {
        service.update(usuario);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletarUsuario(@PathParam("id") Long id) {
        Usuario usuarioId = service.findById(id);
        service.delete(usuarioId);
        return Response.ok("Usuario deletado com sucesso").build();
    }

    @GET
    public Response listarUsuarios() {
        List<Usuario> all = service.findAll();
        List<UsuarioDtoOut> out = all.stream().map(UsuarioDtoOut::dtoOut).toList();
        return Response.ok(out).build();
    }

}
