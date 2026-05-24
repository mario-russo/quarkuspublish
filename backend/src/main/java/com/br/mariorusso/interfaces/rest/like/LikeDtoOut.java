package com.br.mariorusso.interfaces.rest.like;

import com.br.mariorusso.core.model.Curtida;
import com.br.mariorusso.interfaces.rest.publicacao.PublicacaoDtoOut;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDateTime;

@RegisterForReflection
public record LikeDtoOut(  Long id,
                           LocalDateTime data,
                           Long usuario,
                           PublicacaoDtoOut publicacao) {
    public static LikeDtoOut from(Curtida entity){;

        return new LikeDtoOut(
                entity.getId(),
                entity.getDataLike(),
                entity.getUsuario(),
                null
        );


    }
}
