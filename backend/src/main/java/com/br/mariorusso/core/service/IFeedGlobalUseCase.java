package com.br.mariorusso.core.service;

import com.br.mariorusso.core.model.Publicacao;

import java.util.List;

public interface IFeedGlobalUseCase {

   public List<Publicacao> buscarFeedGlobal(int pagina, int tamanhoPagina);
}
