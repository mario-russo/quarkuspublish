package com.br.mariorusso.interfaces.rest.publicacao;

import java.time.LocalDateTime;
import java.util.List;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.ClaimValue;

import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.service.ServiceCore;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/publicacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublicacaoResource {

    @Inject
    ServiceCore<Publicacao> service;
    @Inject
    ServiceCore<Usuario> usuarioService;

    @Inject
    @Claim("id")
    ClaimValue<Long> clamId;

    @POST
    @RolesAllowed("USER")
    public Response salvaPublicacao(PublicacaDtoIn dto) {
        Usuario usuario = usuarioService.findById(clamId.getValue());
        Publicacao publicacao = new Publicacao();
        publicacao.setConteudo(dto.conteudo());
        publicacao.setDataPublicacao(LocalDateTime.now());
        publicacao.setUsuario(usuario);
        service.save(publicacao);
        return Response.ok("Publicação salva com sucesso!!!").build();
    }

    @GET
    public Response ListaPublicacao() {
        try {
            List<Publicacao> publicacao = service.findAll();

            List<PublicacaoDtoOut> dto = publicacao.stream().map(PublicacaoDtoOut::dtoOut).toList();

            return Response.ok(dto).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Nenhuma Publicação encontrada").build();
        }


    }

    @DELETE
    @Path("/{id}")
    public Response deletePublicacao(@PathParam("id") Long id) {
        try {
            Publicacao publicacao = service.findById(id);
            service.delete(publicacao);
            return Response.ok("Publicacão deletada!").build();
        }catch (Exception e ){
            return Response.status(Response.Status.NOT_FOUND).entity("Nenhuma publicação encontrada").build();
        }


    }

    @GET
    @Path("/{id}")
    public Response buscaPublicacaoPorId(@PathParam("id") Long id) {

        try{
            Publicacao publicacao = service.findById(id);
            return Response.ok(publicacao).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Nenhuma publicação encontrada").build();
        }

    }
}
