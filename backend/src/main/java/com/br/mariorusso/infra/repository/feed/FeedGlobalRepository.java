package com.br.mariorusso.infra.repository.feed;

import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.repository.BuscaFeedGlobalRespository;
import com.br.mariorusso.infra.entity.PublicacaoEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
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
