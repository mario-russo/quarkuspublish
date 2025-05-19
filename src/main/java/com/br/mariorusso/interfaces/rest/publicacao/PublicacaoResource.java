package com.br.mariorusso.interfaces.rest.publicacao;

import java.time.LocalDateTime;
import java.util.List;

import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.service.ServiceCore;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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
    @POST
    public Response salvaPublicacao(PublicacaDtoIn dto){
        Usuario usuario = usuarioService.findById(dto.usuario_id());
        
        Publicacao publicacao = new Publicacao();
        publicacao.setConteudo(dto.conteudo());
        publicacao.setDataPublicacao(LocalDateTime.now());
        publicacao.setUsuario(usuario);
        
        service.save(publicacao);
        return Response.ok("Publicação salva com sucesso!!!").build();
    }

    @GET

    public Response ListaPublicacao(){
        List<Publicacao> publicacao = service.findAll();
        return Response.ok(publicacao).build();
    }
}
