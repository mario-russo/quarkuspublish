package com.br.mariorusso.core.service;

import java.util.List;

public interface ServiceCore<T> {
    void save(T object);

    void update(T object);

    void delete(T object);

    T findById(Long id);  
    
    List<T> findAll();
}
