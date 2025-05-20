package com.br.mariorusso.application;

import java.util.List;

import com.br.mariorusso.core.model.Comentario;
import com.br.mariorusso.core.repository.RepositoryCore;
import com.br.mariorusso.core.service.ServiceCore;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class ComentarioUseCase implements ServiceCore<Comentario>{

    @Inject
    RepositoryCore<Comentario> repository;

    @Override
    public void save(Comentario object) {   
        repository.save(object);
        
    }

    @Override
    public void update(Comentario object) {
        repository.update(object);
    }

    @Override
    public void delete(Comentario object) {
        repository.delete(object);
    }

    @Override
    public Comentario findById(Long id) {
        return repository.findById(id);

    }

    @Override
    public List<Comentario> findAll() {
       return repository.findAll();
    }
    
}
