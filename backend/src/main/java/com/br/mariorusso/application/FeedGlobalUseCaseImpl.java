package com.br.mariorusso.application;

import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.repository.BuscaFeedGlobalRespository;
import com.br.mariorusso.core.service.IFeedGlobalUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped
public class FeedGlobalUseCaseImpl implements IFeedGlobalUseCase {

    @Inject
    private BuscaFeedGlobalRespository buscaFeed;


    @Override
    public List<Publicacao> buscarFeedGlobal(int pagina, int tamanhoPagina) {
        return buscaFeed.buscarPublicacoes(pagina,tamanhoPagina);
    }
}
