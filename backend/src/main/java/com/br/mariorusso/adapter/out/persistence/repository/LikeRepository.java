package com.br.mariorusso.adapter.out.infra.repository;

import java.util.List;

import com.br.mariorusso.domain.model.Curtida;
import com.br.mariorusso.application.ports.out.RepositoryCore;
import com.br.mariorusso.adapter.out.infra.entity.LikeEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LikeRepository implements RepositoryCore<Curtida> {

    @Override
    @Transactional
    public void save(Curtida object) {
        LikeEntity entity = LikeEntity.fromDomain(object);
        entity.persist();
    }

    @Override
    @Transactional
    public void update(Curtida object) {
        LikeEntity entity = LikeEntity.findById(object.getId());
        if (entity == null) {
            throw new IllegalArgumentException("curtida não encontrada");
        }
        entity.persist();
    }

    @Override
    @Transactional
    public void delete(Curtida object) {
        LikeEntity entity = LikeEntity.findById(object.getId());
        if (entity == null) {
            throw new IllegalArgumentException("curtida não encontrada");
        }
        entity.delete();;
    }

    @Override
    public Curtida findById(Long id) {
       LikeEntity entity = LikeEntity.findById(id);
       return entity.toDomain();
    }

    @Override
    public List<Curtida> findAll() {
        List<LikeEntity> likes = LikeEntity.listAll();
        return likes.stream().map(LikeEntity::toDomain).toList();
    }

}
