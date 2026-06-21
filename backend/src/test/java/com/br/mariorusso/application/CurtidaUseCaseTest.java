package com.br.mariorusso.application;

import com.br.mariorusso.application.usecase.LikeUseCase;
import com.br.mariorusso.domain.model.Curtida;
import com.br.mariorusso.domain.model.Publicacao;
import com.br.mariorusso.domain.model.Usuario;
import com.br.mariorusso.application.ports.out.RepositoryCore;
import com.br.mariorusso.application.ports.in.ServiceCore;
import com.br.mariorusso.adapter.out.persistence.entity.LikeEntity;
import com.br.mariorusso.adapter.in.rest.exception.NotFoundException;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@QuarkusTest
class CurtidaUseCaseTest {

    @Inject
    LikeUseCase useCase;

    @InjectMock
    RepositoryCore<Curtida> repository;

    @InjectMock
    ServiceCore<Usuario> usuarioRepository;

    @InjectMock
    ServiceCore<Publicacao> publicacaoRepository;

    @Test
    void deve_salvar_curtida_com_sucesso() {

        Curtida curtida = new Curtida();
        curtida.setUsuario(1L);
        curtida.setPublicacao(1L);

        Usuario usuario = new Usuario();
        Publicacao publicacao = new Publicacao();

        try (MockedStatic<LikeEntity> likeMock =
                     Mockito.mockStatic(LikeEntity.class)) {

            likeMock.when(() ->
                            LikeEntity.existsByUsuarioAndPublicacao(1L, 1L))
                    .thenReturn(null);

            when(usuarioRepository.findById(1L))
                    .thenReturn(usuario);

            when(publicacaoRepository.findById(1L))
                    .thenReturn(publicacao);

            useCase.save(curtida);

            verify(repository).save(curtida);
        }
    }

    @Test
    void deve_remover_curtida_quando_ja_existir() {

        Curtida curtida = new Curtida();
        curtida.setUsuario(1L);
        curtida.setPublicacao(1L);

        LikeEntity like = mock(LikeEntity.class);

        try (MockedStatic<LikeEntity> likeMock =
                     Mockito.mockStatic(LikeEntity.class)) {

            likeMock.when(() ->
                            LikeEntity.existsByUsuarioAndPublicacao(1L, 1L))
                    .thenReturn(like);

            useCase.save(curtida);

            verify(like).delete();

            verify(repository, never())
                    .save(any());
        }
    }

    @Test
    void deve_lancar_excecao_quando_usuario_nao_existir() {

        Curtida curtida = new Curtida();
        curtida.setUsuario(1L);
        curtida.setPublicacao(1L);

        try (MockedStatic<LikeEntity> likeMock =
                     Mockito.mockStatic(LikeEntity.class)) {

            likeMock.when(() ->
                            LikeEntity.existsByUsuarioAndPublicacao(1L, 1L))
                    .thenReturn(null);

            when(usuarioRepository.findById(1L))
                    .thenReturn(null);

            NotFoundException exception =
                    assertThrows(
                            NotFoundException.class,
                            () -> useCase.save(curtida)
                    );

            assertEquals(
                    "Usuário não encontrado",
                    exception.getMessage()
            );

            verify(repository, never())
                    .save(any());
        }
    }

    @Test
    void deve_lancar_excecao_quando_publicacao_nao_existir() {

        Curtida curtida = new Curtida();
        curtida.setUsuario(1L);
        curtida.setPublicacao(1L);

        Usuario usuario = new Usuario();

        try (MockedStatic<LikeEntity> likeMock =
                     Mockito.mockStatic(LikeEntity.class)) {

            likeMock.when(() ->
                            LikeEntity.existsByUsuarioAndPublicacao(1L, 1L))
                    .thenReturn(null);

            when(usuarioRepository.findById(1L))
                    .thenReturn(usuario);

            when(publicacaoRepository.findById(1L))
                    .thenReturn(null);

            NotFoundException exception =
                    assertThrows(
                            NotFoundException.class,
                            () -> useCase.save(curtida)
                    );

            assertEquals(
                    "Publicação não encontrada",
                    exception.getMessage()
            );

            verify(repository, never())
                    .save(any());
        }
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