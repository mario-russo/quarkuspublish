package com.br.mariorusso.infra.repository;

import java.util.List;

import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.repository.RepositoryCore;
import com.br.mariorusso.infra.entity.PublicacaoEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class PublicacaoRepository implements RepositoryCore<Publicacao> {

    @Transactional
    @Override
    public void save(Publicacao object) {
        PublicacaoEntity entity = PublicacaoEntity.fromDomain(object);
        entity.persist();
    }

    @Transactional
    @Override
    public void update(Publicacao object) {
        PublicacaoEntity entity = PublicacaoEntity.findById(object.getId());
        if (entity == null) {
            throw new IllegalArgumentException("Publicação não encontrado");
        }
        entity.conteudo = object.getConteudo();
        entity.persist();
    }

    @Transactional
    @Override
    public void delete(Publicacao object) {
        PublicacaoEntity entity = PublicacaoEntity.findById(object.getId());
        if (entity == null) {
            throw new IllegalArgumentException("Publicação não encontrado");
        }
        entity.delete();
    }

    @Override
    public Publicacao findById(Long id) {
        PublicacaoEntity entity = PublicacaoEntity.findById(id);
        if (entity == null) {
            throw new IllegalArgumentException("Publicação não encontrado");
        }
        return entity.toDomain();
    }

    @Override
    public List<Publicacao> findAll() {
        List<PublicacaoEntity> publicacaos = PublicacaoEntity.listAll();
        if (publicacaos.isEmpty()) {
            throw new IllegalArgumentException("Publicação não encontrado");
        }
        return publicacaos.stream().map(PublicacaoEntity::toDomain).toList();
    }

}
