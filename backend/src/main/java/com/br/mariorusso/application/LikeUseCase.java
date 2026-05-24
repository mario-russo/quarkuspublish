package com.br.mariorusso.application;

import java.util.List;

import com.br.mariorusso.core.model.Curtida;
import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.repository.RepositoryCore;
import com.br.mariorusso.core.service.ServiceCore;

import com.br.mariorusso.infra.entity.LikeEntity;
import com.br.mariorusso.interfaces.rest.exception.NotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class LikeUseCase implements ServiceCore<Curtida> {

    @Inject
    RepositoryCore<Curtida> repository;

    @Inject
    ServiceCore<Usuario> usuarioRepository;
    @Inject
    ServiceCore<Publicacao> publicacaoRepository;

    @Override
    @Transactional
    public void save(Curtida curtida) {
        LikeEntity jaCurtiu =
                LikeEntity.existsByUsuarioAndPublicacao(
                        curtida.getUsuario(),
                        curtida.getPublicacao()
                );

        if (jaCurtiu != null) {
            jaCurtiu.delete();
            return;
        }

        Usuario usuarioExiste =
                usuarioRepository.findById(curtida.getUsuario());
        Publicacao publicacaoExiste =
                publicacaoRepository.findById(curtida.getPublicacao());

        if (usuarioExiste ==null) {
            throw new NotFoundException("Usuário não encontrado");
        }

        if (publicacaoExiste == null) {
            throw new NotFoundException("Publicação não encontrada");
        }

        repository.save(curtida);
    }

    @Override
    public void update(Curtida object) {
       repository.update(object);
    }

    @Override
    public void delete(Curtida object) {
        repository.delete(object);
    }

    @Override
    public Curtida findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Curtida> findAll() {
        return repository.findAll();
    }
    
}
