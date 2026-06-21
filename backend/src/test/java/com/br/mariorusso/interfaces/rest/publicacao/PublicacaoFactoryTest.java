package com.br.mariorusso.interfaces.rest.publicacao;

import com.br.mariorusso.domain.model.Publicacao;
import com.br.mariorusso.domain.model.Usuario;

import java.time.LocalDateTime;

public class PublicacaoFactoryTest {

    public static final String CONTEUDO = "conteudo da publicação";

    public static Publicacao instance(Usuario usuario){
        Publicacao publicacao = new Publicacao();
        publicacao.setDataPublicacao(LocalDateTime.now());
        publicacao.setConteudo(CONTEUDO);
        publicacao.setUsuario(usuario);
        return publicacao;
    }
}
