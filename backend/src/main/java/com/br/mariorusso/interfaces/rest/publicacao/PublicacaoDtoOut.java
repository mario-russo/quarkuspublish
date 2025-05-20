package com.br.mariorusso.interfaces.rest.publicacao;

import java.util.List;

import com.br.mariorusso.core.model.Comentario;
import com.br.mariorusso.core.model.Like;
import com.br.mariorusso.core.model.Publicacao;

public record PublicacaoDtoOut(
    Long publicacao_id,
    Long usuario_id,
    String conteudo,
    List<Like> likes,
    List<Comentario> comentarios
) {
    public static PublicacaoDtoOut dtoOut(Publicacao publicacao){
        PublicacaoDtoOut dto = new PublicacaoDtoOut(publicacao.getId(),
                 publicacao.getUsuario().getId(),
                 publicacao.getConteudo(), 
                 publicacao.getLikes(), 
                 publicacao.getComentarios());
                 
        return dto;
    }
}
