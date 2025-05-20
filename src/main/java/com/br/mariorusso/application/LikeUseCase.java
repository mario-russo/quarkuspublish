package com.br.mariorusso.application;

import java.util.List;

import com.br.mariorusso.core.model.Like;
import com.br.mariorusso.core.repository.RepositoryCore;
import com.br.mariorusso.core.service.ServiceCore;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;



@ApplicationScoped
public class LikeUseCase implements ServiceCore<Like> {

    @Inject
    RepositoryCore<Like> repository;

    @Override
    public void save(Like object) {
        repository.save(object);
    }

    @Override
    public void update(Like object) {
       repository.update(object);
    }

    @Override
    public void delete(Like object) {
        repository.delete(object);
    }

    @Override
    public Like findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Like> findAll() {
        return repository.findAll();
    }
    
}
