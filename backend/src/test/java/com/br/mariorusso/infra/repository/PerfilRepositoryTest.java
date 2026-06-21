package com.br.mariorusso.infra.repository;

import com.br.mariorusso.adapter.out.persistence.repository.PerfilRepository;
import com.br.mariorusso.domain.model.Perfil;
import com.br.mariorusso.adapter.out.persistence.entity.PerfilEntity;
import com.br.mariorusso.adapter.out.persistence.entity.UsuarioEntity;
import com.br.mariorusso.adapter.in.rest.exception.NotFoundException;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.mock.PanacheMock; // Usando a biblioteca do seu pom.xml
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PerfilRepositoryTest {

    @Inject
    PerfilRepository perfilRepository;

    @BeforeEach
    void setUp() {

        PanacheMock.mock(UsuarioEntity.class);
        PanacheMock.mock(PerfilEntity.class);
    }

    @Test
    @DisplayName("Deve salvar perfil quando usuário existir")
    void deveSalvarPerfilComSucessoQuandoUsuarioExistir() {
        Long usuarioIdExistente = 1L;

        Perfil perfilDomain = new Perfil()
                .setTitulo("Dev Java")
                .setSobre("Trabalho com Quarkus")
                .setUsuarioId(usuarioIdExistente);

        UsuarioEntity usuarioMock = new UsuarioEntity();
        usuarioMock.id = usuarioIdExistente;

        PerfilEntity entityMock = Mockito.mock(PerfilEntity.class);


        PanacheMock.doReturn(usuarioMock).when(UsuarioEntity.class);
        UsuarioEntity.findById(usuarioIdExistente);


        PanacheMock.doReturn(entityMock).when(PerfilEntity.class);
        PerfilEntity.fromDomain(perfilDomain,usuarioMock);

        perfilRepository.save(perfilDomain);


        Mockito.verify(entityMock, Mockito.times(1)).persist();
    }

    @Test
    @DisplayName("Deve lançar exception quando usuário não existir")
    void deveLancarExceptionQuandoUsuarioNaoForEncontrado() {
        Long usuarioIdInexistente = 999L;
        Perfil perfilDomain = new Perfil().setUsuarioId(usuarioIdInexistente);


        PanacheMock.doReturn(null).when(UsuarioEntity.class);
        UsuarioEntity.findById(usuarioIdInexistente);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> perfilRepository.save(perfilDomain)
        );

        assertEquals("Usuário não encontradado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve deletar perfil com sucesso")
    void deveDeletarPerfilComSucessoQuandoExistir() {
        Long usuarioId = 1L;
        Perfil perfilDomain = new Perfil().setUsuarioId(usuarioId);

        PerfilEntity entityMock = Mockito.mock(PerfilEntity.class);
        PanacheQuery<PerfilEntity> queryMock = Mockito.mock(PanacheQuery.class);


        PanacheMock.doReturn(queryMock).when(PerfilEntity.class);
        PerfilEntity.find(Mockito.anyString(), (Object[]) Mockito.any());

        Mockito.when(queryMock.firstResult()).thenReturn(entityMock);

        perfilRepository.delete(perfilDomain);


        Mockito.verify(entityMock, Mockito.times(1)).delete();
    }

    @Test
    @DisplayName("Deve lançar exception ao deletar perfil inexistente")
    void deveLancarExceptionAoDeletarPerfilInexistente() {
        Long usuarioId = 999L;
        Perfil perfilDomain = new Perfil().setUsuarioId(usuarioId);

        PanacheQuery<PerfilEntity> queryMock = Mockito.mock(PanacheQuery.class);

        PanacheMock.doReturn(queryMock).when(PerfilEntity.class);
        PerfilEntity.find(Mockito.anyString(), (Object[]) Mockito.any());

        Mockito.when(queryMock.firstResult()).thenReturn(null);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> perfilRepository.delete(perfilDomain)
        );

        assertEquals("Perfil não encontrado!", exception.getMessage());
    }
    @Test
    @DisplayName("Deve buscar perfil por usuário com sucesso")
    void deveBuscarPerfilPorUsuarioComSucesso() {

        Long usuarioId = 1L;


        PerfilEntity entityMock = Mockito.mock(PerfilEntity.class);
        entityMock.titulo = "Dev Java";
        entityMock.sobre = "Trabalho com Quarkus";


        Perfil perfilEsperado = new Perfil()
                .setTitulo("Dev Java")
                .setSobre("Trabalho com Quarkus")
                .setUsuarioId(usuarioId); // Ajustado se seu método for setUsuario_id


        Mockito.when(entityMock.toDomain()).thenReturn(perfilEsperado);

        PanacheQuery<PerfilEntity> queryMock = Mockito.mock(PanacheQuery.class);


        PanacheMock.doReturn(queryMock).when(PerfilEntity.class);
        PerfilEntity.find(Mockito.anyString(), (Object[]) Mockito.any());


        Mockito.when(queryMock.firstResult()).thenReturn(entityMock);


        Perfil resultado = perfilRepository.findById(usuarioId);


        assertNotNull(resultado);
        assertEquals("Dev Java", resultado.getTitulo());
        assertEquals("Trabalho com Quarkus", resultado.getSobre());
    }
    @Test
    @DisplayName("Deve atualizar perfil com sucesso quando existir")
    void deveAtualizarPerfilComSucessoQuandoExistir() {

        Long usuarioId = 1L;
        Perfil perfilDomain = new Perfil()
                .setTitulo("Novo Titulo")
                .setSobre("Nova Descricao")
                .setUsuarioId(usuarioId);


        PerfilEntity entityReal = new PerfilEntity();
        entityReal.titulo = "Titulo Antigo";
        entityReal.sobre = "Sobre Antigo";
        PanacheQuery<PerfilEntity> queryMock = Mockito.mock(PanacheQuery.class);


        PanacheMock.doReturn(queryMock).when(PerfilEntity.class);
        PerfilEntity.find(Mockito.anyString(), (Object[]) Mockito.any());

        Mockito.when(queryMock.firstResult()).thenReturn(entityReal);

        perfilRepository.update(perfilDomain);

        assertEquals("Novo Titulo", entityReal.titulo);
        assertEquals("Nova Descricao", entityReal.sobre);
    }

    @Test
    @DisplayName("Deve lançar exception ao tentar atualizar perfil inexistente")
    void deveLancarExceptionAoTentarAtualizarPerfilInexistente() {
        Long usuarioIdInexistente = 999L;
        Perfil perfilDomain = new Perfil()
                .setTitulo("Titulo")
                .setSobre("Sobre")
                .setUsuarioId(usuarioIdInexistente);

        PanacheQuery<PerfilEntity> queryMock = Mockito.mock(PanacheQuery.class);

        PanacheMock.doReturn(queryMock).when(PerfilEntity.class);
        PerfilEntity.find(Mockito.anyString(), (Object[]) Mockito.any());

        Mockito.when(queryMock.firstResult()).thenReturn(null);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> perfilRepository.update(perfilDomain)
        );

        assertEquals("Perfil não encontrado!!!", exception.getMessage());
    }

}
