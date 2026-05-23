package com.br.mariorusso.interfaces.rest.like;

import com.br.mariorusso.infra.entity.LikeEntity;
import com.br.mariorusso.interfaces.rest.usuario.UsuarioDtoOut;

public record CurtidaForPublicacao(
        Long id,
        UsuarioDtoOut usuario

) {

    public static CurtidaForPublicacao dto(LikeEntity entity){
        UsuarioDtoOut usuario1 = new UsuarioDtoOut(entity.usuario.id, entity.usuario.nome, null);
        return new CurtidaForPublicacao(entity.id, usuario1);
    }
}
