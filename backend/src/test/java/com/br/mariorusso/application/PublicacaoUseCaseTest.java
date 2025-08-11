package com.br.mariorusso.application;

import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.repository.RepositoryCore;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@QuarkusTest
class PublicacaoUseCaseTest {
    @Inject
    PublicacaoUseCase useCase;

    @InjectMock
    RepositoryCore<Publicacao> repository;


    @Test
    void save() {
        Publicacao publicacao = new Publicacao();
        publicacao.setId(1L);

        useCase.save(publicacao);

        verify(repository, times(1)).save(publicacao);
    }

    @Test
    void update() {
        Publicacao publicacao = new Publicacao();
        publicacao.setId(1L);

        useCase.update(publicacao);
        verify(repository, times(1)).update(publicacao);
    }

    @Test
    void delete() {

        Publicacao publicacao = new Publicacao();
        useCase.delete(publicacao);
        verify(repository).delete(publicacao);
    }

    @Test
    void findById() {
        Publicacao publicacao = new Publicacao();
        publicacao.setId(1L);
        publicacao.setConteudo("novo conteudo");

        Mockito.when(useCase.findById(any())).thenReturn(publicacao);

        var pub = useCase.findById(1L);

        assertEquals(publicacao.getConteudo(), pub.getConteudo());
        assertEquals(1L, pub.getId());

        verify(repository, times(1)).findById(1L);


    }

    @Test
    void findAll() {
        Publicacao p1 = new Publicacao();
        p1.setId(1L);
        p1.setConteudo("1");

        Publicacao p2 = new Publicacao();
        p1.setId(2L);
        p1.setConteudo("2");

        Publicacao p3 = new Publicacao();
        p1.setId(3L);
        p1.setConteudo("3");

        List<Publicacao> list = List.of(p1,p2,p3);

        Mockito.when(useCase.findAll()).thenReturn(list);

        var listPub = useCase.findAll();

        assertEquals(3, listPub.size());
        assertEquals(p1.getId(), listPub.getFirst().getId());
        assertEquals(p1.getConteudo(), listPub.getFirst().getConteudo());

        verify(repository, times(1)).findAll();
    }
}