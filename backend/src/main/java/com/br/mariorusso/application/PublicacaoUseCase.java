package com.br.mariorusso.application;

import java.util.List;

import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.repository.RepositoryCore;
import com.br.mariorusso.core.service.ServiceCore;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicacaoUseCase implements ServiceCore<Publicacao>  {
    
    @Inject
    RepositoryCore<Publicacao> repository;

    @Override
    public void save(Publicacao object) {
        repository.save(object);
    }

    @Override
    public void update(Publicacao object) {
        repository.update(object);
    }

    @Override
    public void delete(Publicacao object) {
        repository.delete(object);
    }

    @Override
    public Publicacao findById(Long id) {
        Publicacao publicacao = repository.findById(id);
        return publicacao;
    }

    @Override
    public List<Publicacao> findAll() {
       return repository.findAll();
    }

}
