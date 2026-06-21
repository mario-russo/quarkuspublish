package com.br.mariorusso.adapter.in.rest.publicacao;

import java.time.LocalDateTime;
import java.util.List;

import com.br.mariorusso.domain.model.Publicacao;
import com.br.mariorusso.adapter.in.rest.comentarios.ComentaioDtoOut;

import com.br.mariorusso.adapter.in.rest.like.LikeDtoOut;
import com.br.mariorusso.adapter.in.rest.usuario.UsuarioDtoOut;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record PublicacaoDtoOut(
    Long publicacao_id,
    UsuarioDtoOut usuario,
    String conteudo,
    List<LikeDtoOut> likes,
    List<ComentaioDtoOut> comentarios,
    LocalDateTime dataPublicacao
) {
    public static PublicacaoDtoOut dtoOut(Publicacao publicacao){
        List<LikeDtoOut> like = publicacao.getLikes().stream().map(LikeDtoOut::from).toList();
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
