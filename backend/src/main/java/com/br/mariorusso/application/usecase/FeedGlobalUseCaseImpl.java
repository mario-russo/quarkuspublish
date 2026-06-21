package com.br.mariorusso.application;

import com.br.mariorusso.domain.model.Publicacao;
import com.br.mariorusso.application.ports.out.BuscaFeedGlobalRespository;
import com.br.mariorusso.application.ports.in.IFeedGlobalUseCase;
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
