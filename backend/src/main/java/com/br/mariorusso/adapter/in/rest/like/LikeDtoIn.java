package com.br.mariorusso.adapter.in.rest.like;



import java.time.LocalDateTime;

public record LikeDtoIn(
    Long usuario_id,
    Long publicacao_id,
    LocalDateTime dataLike
) {

}
