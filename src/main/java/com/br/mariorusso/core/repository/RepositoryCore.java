package com.br.mariorusso.core.repository;

import java.util.List;

public interface RepositoryCore<T> {
    void save(T object);

    void update(T object);

    void delete(T object);

    T findById(Long id);

    List<T> findAll();
}
