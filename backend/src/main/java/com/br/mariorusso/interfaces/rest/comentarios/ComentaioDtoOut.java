package com.br.mariorusso.interfaces.rest.comentarios;

import com.br.mariorusso.core.model.Comentario;
import com.br.mariorusso.interfaces.rest.publicacao.PublicacaoDtoOut;
import com.br.mariorusso.interfaces.rest.usuario.UsuarioDtoOut;
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
