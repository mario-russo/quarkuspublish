package com.br.mariorusso.interfaces.rest.comentarios;

import java.time.LocalDateTime;


import com.br.mariorusso.application.ComentarioUseCase;
import com.br.mariorusso.application.PublicacaoUseCase;
import com.br.mariorusso.application.UsuarioUseCase;
import com.br.mariorusso.core.model.Comentario;
import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.service.ServiceCore;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("comentarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComentarioResource {

    private final ServiceCore<Comentario> service;
    private final ServiceCore<Publicacao> servicePub;
    private final ServiceCore<Usuario> serviceUser;

    @Inject
    public  ComentarioResource(ComentarioUseCase service, PublicacaoUseCase servicePub, UsuarioUseCase serviceUser){
        this.service = service;
        this.servicePub = servicePub;
        this.serviceUser = serviceUser;
    }


    @POST 
    public Response salvaComentrio(ComentarioDtoIn dto){
        try {
            Usuario usuario = serviceUser.findById(dto.usuario_id());
            Publicacao publicacao = servicePub.findById(dto.publicacao_id());

            Comentario comentario = new Comentario();
            comentario.setConteudo(dto.conteudo());
            comentario.setDataComentario(LocalDateTime.now());
            comentario.setPublicacao(publicacao);
            comentario.setUsuario(usuario);
            service.save(comentario);

            return Response.ok("comentario salvo").build();

        }catch (Exception e){
            return  Response.status(Response.Status.NOT_FOUND).entity("Erro ao comentar!!!").build();
        }

    }

    @GET
    public Response buscaTodosComentarios(){
       return Response.ok( service.findAll()).build();
    }

    @Path("/{id}")
    @GET
    public Response BuscacomentarioPorId(@PathParam("id")Long id){
        Comentario comentario = service.findById(id);
        return Response.ok(comentario).build();
    }
    
}
