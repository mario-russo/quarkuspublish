package com.br.mariorusso.adapter.in.rest.comentarios;

import com.br.mariorusso.domain.model.Comentario;
import com.br.mariorusso.adapter.in.rest.publicacao.PublicacaoDtoOut;
import com.br.mariorusso.adapter.in.rest.usuario.UsuarioDtoOut;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDateTime;
@RegisterForReflection
public record ComentaioDtoOut(
         Long id,
         String conteudo,
         LocalDateTime dataComentario,
         UsuarioDtoOut usuario,
         PublicacaoDtoOut publicacao

) {

    public static ComentaioDtoOut from (Comentario comentario){
        return new ComentaioDtoOut(
                comentario.getId(),
                comentario.getConteudo(),
                comentario.getDataComentario(),
                UsuarioDtoOut.dtoOut(comentario.getUsuario()),
                PublicacaoDtoOut.dtoOut(comentario.getPublicacao())
        );
    }
}
