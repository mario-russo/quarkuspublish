package com.br.mariorusso.application;

import com.br.mariorusso.application.usecase.SalvaPerfilUseCaseImpl;
import com.br.mariorusso.domain.model.Perfil;
import com.br.mariorusso.application.ports.out.RepositoryCore;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@QuarkusTest
class SalvaPerfilUseCaseImplTest {
    @Inject
    SalvaPerfilUseCaseImpl service;
    @InjectMock
    RepositoryCore<Perfil> repositoryCore;

    private Perfil perfil;

    @BeforeEach
    void setUp() {
        perfil = new Perfil().setTitulo("novo titulo").setUsuarioId(1L).setSobre("Eu mesmo!");
    }
    @Test
    @DisplayName("Deve salvar perfil")
    void salvarPerfil(){
        when(repositoryCore.findById(perfil.getUsuarioId())).thenReturn(null);

        service.execute(perfil);

        verify(repositoryCore).save(perfil);
        verify(repositoryCore, never()).update(any());
    }
    @Test
    @DisplayName("Deve atualizar perfil quando já existir")
    void deveAtualizarQuandoPerfilJaExistir() {
        when(repositoryCore.findById(perfil.getUsuarioId())).thenReturn(perfil);

        service.execute(perfil);

        verify(repositoryCore).update(perfil);
        verify(repositoryCore, never()).save(any());
    }


}