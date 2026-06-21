package com.br.mariorusso.application.ports.in;

public interface LoginCore<T>{
    public T login(String senha, String email);
}
