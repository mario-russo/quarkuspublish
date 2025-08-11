package com.br.mariorusso.application;

import com.br.mariorusso.core.model.Like;
import com.br.mariorusso.core.repository.RepositoryCore;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@QuarkusTest
class LikeUseCaseTest {

    @Inject
    LikeUseCase useCase;

    @InjectMock
    RepositoryCore<Like> repository;

    @Test
    void save() {

        Like like = new Like();
        like.setId(1L);
        useCase.save(like);

        verify(repository, times(1)).save(like);
    }

    @Test
    void update() {
        Like like = new Like();
        like.setId(1L);
        useCase.update(like);

        verify(repository, times(1)).update(like);
    }

    @Test
    void delete() {
        Like like = new Like();
        like.setId(1L);
        useCase.delete(like);

        verify(repository, times(1)).delete(like);
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