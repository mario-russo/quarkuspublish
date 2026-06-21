package com.br.mariorusso.adapter.out.persistence.repository;

import java.util.List;

import com.br.mariorusso.domain.model.Publicacao;
import com.br.mariorusso.application.ports.out.RepositoryCore;
import com.br.mariorusso.adapter.out.persistence.entity.PublicacaoEntity;

import com.br.mariorusso.adapter.in.rest.exception.NotFoundException;
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
            throw new NotFoundException("Publicação não Encontrada!");
        }
        entity.conteudo = object.getConteudo();
        entity.persist();
    }

    @Transactional
    @Override
    public void delete(Publicacao object) {
        PublicacaoEntity entity = PublicacaoEntity.findById(object.getId());
        if (entity == null) {
            throw new NotFoundException("Publicação não Encontrada!");
        }
        entity.delete();
    }

    @Override
    public Publicacao findById(Long id) {
        PublicacaoEntity entity = PublicacaoEntity.findById(id);
        if (entity == null) {
            throw new NotFoundException("Publicação não Encontrada!");
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

    public List<Publicacao> findByfild(String key, String value){

        List<PublicacaoEntity> list = PublicacaoEntity.list(key, value);
        List<Publicacao> publicacaos = list.stream().map(PublicacaoEntity::toDomain).toList();
        return publicacaos;
    }
    public List<Publicacao> findByfild(String key, Long value){

        List<PublicacaoEntity> list = PublicacaoEntity.list(key, value);
        List<Publicacao> publicacaos = list.stream().map(PublicacaoEntity::toDomain).toList();
        return publicacaos;
    }

}
