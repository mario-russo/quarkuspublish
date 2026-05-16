package com.br.mariorusso.interfaces.rest.like;

import com.br.mariorusso.core.model.Like;
import com.br.mariorusso.interfaces.rest.publicacao.PublicacaoDtoOut;
import com.br.mariorusso.interfaces.rest.usuario.UsuarioDtoOut;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDateTime;

public record LikeDtoIn(
    Long usuario_id,
    Long publicacao_id,
    LocalDateTime dataLike
) {

    @RegisterForReflection
    public static record LikeDtoOut(
             Long id,
             LocalDateTime data,
             UsuarioDtoOut usuario,
             PublicacaoDtoOut publicacao) {


        public static LikeDtoOut from(Like entity){
        LikeDtoOut like= new LikeDtoOut(
                entity.getId(),
                entity.getDataLike(),
                UsuarioDtoOut.dtoOut(entity.getUsuario()),
                PublicacaoDtoOut.dtoOut(entity.getPublicacao()));

            return like;
        }
    }
}
