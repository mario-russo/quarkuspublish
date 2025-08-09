package com.br.mariorusso.interfaces.rest.like;

import java.time.LocalDateTime;
import java.util.List;

import com.br.mariorusso.core.model.Like;
import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.service.ServiceCore;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.ClaimValue;

@Path("/like")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LikeResource {

    @Inject
    ServiceCore<Like> service;
    @Inject
    ServiceCore<Publicacao> servicePub;
    @Inject
    ServiceCore<Usuario> serviceUser;

    @Claim("id")
    ClaimValue<Long> idClaim;

    @POST
    @RolesAllowed("USER")
    public Response salvaLikes(LikeDtoIn dto) {
        try {
            Usuario usuario = serviceUser.findById(idClaim.getValue());

            Publicacao publicacao = servicePub.findById(dto.publicacao_id());

            Like like = new Like();

            like.setDataLike(LocalDateTime.now());
            like.setPublicacao(publicacao);
            like.setUsuario(usuario);
            service.save(like);
            return Response.ok("Publicação Curtida").build();


        } catch (Exception e) {
            return Response.status(404).build();
        }

    }

    @GET
    public Response buscaTodosLikes() {
        List<Like> like = service.findAll();
        return Response.ok(like).build();
    }

    @Path("/{id}")
    @GET
    public Response BuscaLikePorId(@PathParam("id") Long id) {

        try {
            Like byId = service.findById(id);
            return Response.ok(byId).build();

        } catch (Exception e) {
            return Response.status(404).entity("sem curtida").build();
        }

    }
}
