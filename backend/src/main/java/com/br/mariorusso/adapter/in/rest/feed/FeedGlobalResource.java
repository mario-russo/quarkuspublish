package com.br.mariorusso.interfaces.rest.feed;


import com.br.mariorusso.domain.model.Publicacao;
import com.br.mariorusso.application.ports.in.service.IFeedGlobalUseCase;
import com.br.mariorusso.interfaces.rest.publicacao.PublicacaoDtoOut;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/feed")
public class FeedGlobalResource {

    @Inject
    private IFeedGlobalUseCase feedUseCase;

    @Path("/global")
    @RolesAllowed("USER")
    @GET
    public Response buscaFeed(
            @QueryParam("pagina") @DefaultValue("0") int pagina,
            @QueryParam("tamanho") @DefaultValue("100") int tamanho
    ){

        List<Publicacao> publicacaos = feedUseCase.buscarFeedGlobal(pagina, tamanho);
        List<PublicacaoDtoOut> response =
                publicacaos.stream()
                        .map(PublicacaoDtoOut::dtoOut)
                        .toList();
        return Response.ok(response).build();

    }
}
