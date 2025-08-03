package com.br.mariorusso.application;

import java.util.List;

import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.repository.RepositoryCore;
import com.br.mariorusso.core.service.ServiceCore;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuarioUseCase implements ServiceCore<Usuario>{

    @Inject
    private RepositoryCore<Usuario> repository;

    @Override
    public void save(Usuario object) {
        repository.save(object);
    }

    @Override
    public void update(Usuario object) {
        repository.update(object);
    }

    @Override
    public void delete(Usuario object) {
        repository.delete(object);
    }

    @Override
    public Usuario findById(Long id) {
         Usuario usuario = repository.findById(id);
        return usuario;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = repository.findAll();
        return usuarios;
    }
    
}
