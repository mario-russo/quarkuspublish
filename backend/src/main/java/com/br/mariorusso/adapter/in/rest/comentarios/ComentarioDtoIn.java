package com.br.mariorusso.adapter.in.rest.comentarios;

import java.time.LocalDateTime;

public record ComentarioDtoIn(
        long usuario_id,
        long publicacao_id,
        String conteudo,
        LocalDateTime dataComenatario) {
    
}
