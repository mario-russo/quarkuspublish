package com.br.mariorusso.core.repository;

import com.br.mariorusso.core.model.Publicacao;

import java.util.List;

public interface BuscaFeedGlobalRespository {
    public List<Publicacao> buscarPublicacoes(int pagina, int tamanhoPagina);
}
