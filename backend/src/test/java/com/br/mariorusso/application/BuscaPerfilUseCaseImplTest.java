package com.br.mariorusso.application;

import com.br.mariorusso.core.model.Perfil;
import com.br.mariorusso.core.repository.RepositoryCore;
import com.br.mariorusso.interfaces.rest.exception.NotFoundException;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@QuarkusTest
class BuscaPerfilUseCaseImplTest {

    @Inject
    BuscaPerfilUseCaseImpl useCase;

    @InjectMock
    RepositoryCore<Perfil> repository;

    @Test
    @DisplayName("Deve retornar perfil quando existir")
    void deveRetornarPerfilQuandoExistir() {

        Long usuarioId = 1L;

        Perfil perfilMock = new Perfil()
                .setUsuarioId(usuarioId)
                .setTitulo("Dev Java")
                .setSobre("Backend com Quarkus");

        when(repository.findById(usuarioId))
                .thenReturn(perfilMock);

        Perfil resultado = useCase.exec(usuarioId);

        assertNotNull(resultado);

        assertEquals(
                perfilMock.getUsuarioId(),
                resultado.getUsuarioId()
        );

        assertEquals(
                perfilMock.getTitulo(),
                resultado.getTitulo()
        );

        verify(repository, times(1))
                .findById(usuarioId);
    }

    @Test
    @DisplayName("Deve lançar exception quando perfil não existir")
    void deveLancarExceptionQuandoPerfilNaoExistir() {

        Long usuarioId = 999L;

        when(repository.findById(usuarioId))
                .thenReturn(null);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> useCase.exec(usuarioId)
        );

        assertEquals(
                "Perfil não encontrado!",
                exception.getMessage()
        );

        verify(repository, times(1))
                .findById(usuarioId);
    }
}