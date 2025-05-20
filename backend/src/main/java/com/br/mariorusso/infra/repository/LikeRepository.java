package com.br.mariorusso.infra.repository;

import java.util.List;

import com.br.mariorusso.core.model.Like;
import com.br.mariorusso.core.repository.RepositoryCore;
import com.br.mariorusso.infra.entity.LikeEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LikeRepository implements RepositoryCore<Like> {

    @Override
    @Transactional
    public void save(Like object) {
        LikeEntity entity = LikeEntity.fromDomain(object);
        entity.persist();
    }

    @Override
    @Transactional
    public void update(Like object) {
        LikeEntity entity = LikeEntity.findById(object.getId());
        if (entity == null) {
            throw new IllegalArgumentException("curtida não encontrada");
        }
        entity.persist();
    }

    @Override
    @Transactional
    public void delete(Like object) {
        LikeEntity entity = LikeEntity.findById(object.getId());
        if (entity == null) {
            throw new IllegalArgumentException("curtida não encontrada");
        }
        entity.delete();;
    }

    @Override
    public Like findById(Long id) {
       LikeEntity entity = LikeEntity.findById(id);
       return entity.toDomain();
    }

    @Override
    public List<Like> findAll() {
        List<LikeEntity> likes = LikeEntity.listAll();
        return likes.stream().map(LikeEntity::toDomain).toList();
    }

}
