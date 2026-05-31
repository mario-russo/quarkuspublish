package com.br.mariorusso.core.service;

import com.br.mariorusso.core.model.Perfil;

public interface IBuscaPerfilUseCase {

    public Perfil exec(Long usuarioId);
}
