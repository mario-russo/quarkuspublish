package com.br.mariorusso.application.ports.in.service;

import com.br.mariorusso.domain.model.Perfil;

public interface IBuscaPerfilUseCase {

    public Perfil exec(Long usuarioId);
}
