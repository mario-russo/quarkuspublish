package com.br.mariorusso.application.ports.in;

import com.br.mariorusso.domain.model.Publicacao;

import java.util.List;

public interface IFeedGlobalUseCase {

   public List<Publicacao> buscarFeedGlobal(int pagina, int tamanhoPagina);
}
