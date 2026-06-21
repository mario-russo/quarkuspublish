package com.br.mariorusso.adapter.out.persistence.repository.feed;

import com.br.mariorusso.domain.model.Publicacao;
import com.br.mariorusso.application.ports.out.BuscaFeedGlobalRespository;
import com.br.mariorusso.adapter.out.persistence.entity.PublicacaoEntity;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;


@ApplicationScoped
public class FeedGlobalRepository  implements BuscaFeedGlobalRespository {

    @Override
    public List<Publicacao> buscarPublicacoes(int pagina, int tamanhoPagina) {
        List<PublicacaoEntity> publicacoes =
                PublicacaoEntity.findAll(
                                Sort.descending("dataPublicacao"))
                        .page(Page.of(pagina, tamanhoPagina))
                        .list();
        return publicacoes.stream().map(e-> e.toDomain()).toList();
    }
}
