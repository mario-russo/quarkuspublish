package com.br.mariorusso.interfaces.rest.publicacao;

import java.time.LocalDateTime;
import java.util.List;

import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.interfaces.rest.comentarios.ComentaioDtoOut;

import com.br.mariorusso.interfaces.rest.like.LikeDtoIn;
import com.br.mariorusso.interfaces.rest.usuario.UsuarioDtoOut;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record PublicacaoDtoOut(
    Long publicacao_id,
    UsuarioDtoOut usuario,
    String conteudo,
    List<LikeDtoIn.LikeDtoOut> likes,
    List<ComentaioDtoOut> comentarios,
    LocalDateTime dataPublicacao
) {
    public static PublicacaoDtoOut dtoOut(Publicacao publicacao){
        List<LikeDtoIn.LikeDtoOut> like = publicacao.getLikes().stream().map(LikeDtoIn.LikeDtoOut::from).toList();
        List<ComentaioDtoOut> comentario = publicacao.getComentarios().stream().map(ComentaioDtoOut::from).toList();
        PublicacaoDtoOut dto = new PublicacaoDtoOut(publicacao.getId(),
                 UsuarioDtoOut.dtoOut(publicacao.getUsuario()),
                 publicacao.getConteudo(),
                 like,
                 comentario,
                 publicacao.getDataPublicacao());

        return dto;
    }
}
