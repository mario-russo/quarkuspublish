package com.br.mariorusso.core.service;

public interface LoginCore<T>{
    public T login(String senha, String email);
}
