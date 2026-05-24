package com.br.mariorusso.application;

import com.br.mariorusso.core.model.Curtida;
import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.repository.RepositoryCore;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@QuarkusTest
class CurtidaUseCaseTest {

    @Inject
    LikeUseCase useCase;

    @InjectMock
    RepositoryCore<Curtida> repository;

    @Test
    void save() {

        Curtida curtida = new Curtida();
        curtida.setId(1L);
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Publicacao publicacao =new Publicacao();
        publicacao.setId(1L);

        curtida.setUsuario(usuario.getId());
        curtida.setPublicacao(1L);
        useCase.save(curtida);

        verify(repository, times(1)).save(curtida);
    }

    @Test
    void update() {
        Curtida curtida = new Curtida();
        curtida.setId(1L);
        useCase.update(curtida);

        verify(repository, times(1)).update(curtida);
    }

    @Test
    void delete() {
        Curtida curtida = new Curtida();
        curtida.setId(1L);
        useCase.delete(curtida);

        verify(repository, times(1)).delete(curtida);
    }

    @Test
    void findById() {
        useCase.findById(1L);
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void findAll() {
        useCase.findAll();
        verify(repository, times(1)).findAll();
    }
}