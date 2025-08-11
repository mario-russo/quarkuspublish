package com.br.mariorusso.application;

import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.repository.RepositoryCore;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@QuarkusTest
class UsuarioUseCaseTest {

    @Inject
    UsuarioUseCase useCase;

    @InjectMock
    RepositoryCore<Usuario> repo;

    @Test
    void save() {
        useCase.save(Mockito.any());
        verify(repo).save(Mockito.any());
    }

    @Test
    void update() {
        useCase.update(Mockito.any());
        verify(repo).update(Mockito.any());
    }

    @Test
    void delete() {
        useCase.delete(Mockito.any());
        verify(repo).delete(Mockito.any());
    }

    @Test
    void findById() {
        Usuario usuairo = new Usuario();
        usuairo.setId(1L);

        Mockito.when(useCase.findById(1L)).thenReturn(usuairo);
       var user =  useCase.findById(1L);

       assertEquals(1L, user.getId());

       verify(repo).findById(1L);

    }

    @Test
    void findAll() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("mario");

        List<Usuario> list = List.of(usuario);

        Mockito.when(useCase.findAll()).thenReturn(list);
        var listMock = useCase.findAll();
        assertEquals(1, listMock.size());

        verify(repo, times(1)).findAll();

    }
}