package com.br.mariorusso.application.ports.out.repository;

import com.br.mariorusso.domain.model.Publicacao;

import java.util.List;

public interface BuscaFeedGlobalRespository {
    public List<Publicacao> buscarPublicacoes(int pagina, int tamanhoPagina);
}
