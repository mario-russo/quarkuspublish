package com.br.mariorusso.application;

import com.br.mariorusso.core.model.Comentario;
import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.repository.RepositoryCore;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.mockito.Mockito.times;

@QuarkusTest
class ComentarioUseCaseTest {

    @Inject
    ComentarioUseCase useCase;

    @InjectMock
    RepositoryCore<Comentario> repository;




    @Test
    void save() {
        Usuario user = new Usuario(1L, "mario russo", "mario@gmail.com","12345");
        Publicacao pub = new Publicacao(1L,"nova publicação", LocalDateTime.now(),user);

        Comentario comentario = new Comentario(1L,"novo Comentario", LocalDateTime.now(), user, pub);
        useCase.save(comentario);

        Mockito.verify(repository, times(1)).save(comentario);

    }

    @Test
    void update() {
        Usuario user = new Usuario(1L, "mario russo", "mario@gmail.com","12345");
        Publicacao pub = new Publicacao(1L,"nova publicação", LocalDateTime.now(),user);

        Comentario comentario = new Comentario(1L,"novo Comentario", LocalDateTime.now(), user, pub);
        useCase.update(comentario);

        Mockito.verify(repository, times(1)).update(comentario);
    }

    @Test
    void delete() {
        Usuario user = new Usuario(1L, "mario russo", "mario@gmail.com","12345");
        Publicacao pub = new Publicacao(1L,"nova publicação", LocalDateTime.now(),user);

        Comentario comentario = new Comentario(1L,"novo Comentario", LocalDateTime.now(), user, pub);
        useCase.delete(comentario);

        Mockito.verify(repository, times(1)).delete(comentario);
    }

    @Test
    void findById() {
        useCase.findById(1L);

        Mockito.verify(repository, times(1)).findById(1L);
    }

    @Test
    void findAll() {
        useCase.findAll();
        Mockito.verify(repository,times(1)).findAll();
    }
}