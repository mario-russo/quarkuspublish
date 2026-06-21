package com.br.mariorusso.adapter.in.rest.like;

import com.br.mariorusso.adapter.out.persistence.entity.LikeEntity;
import com.br.mariorusso.adapter.in.rest.usuario.UsuarioDtoOut;

public record CurtidaForPublicacao(
        Long id,
        UsuarioDtoOut usuario

) {

    public static CurtidaForPublicacao dto(LikeEntity entity){
        UsuarioDtoOut usuario1 = new UsuarioDtoOut(entity.usuario.id, entity.usuario.nome, null);
        return new CurtidaForPublicacao(entity.id, usuario1);
    }
}
