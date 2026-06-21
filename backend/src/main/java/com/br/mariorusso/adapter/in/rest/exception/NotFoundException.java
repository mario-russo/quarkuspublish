package com.br.mariorusso.interfaces.rest.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String menssagem){
        super(menssagem);
    }
}
