package com.br.mariorusso.application.ports.in;

import com.br.mariorusso.domain.model.Perfil;

public interface IBuscaPerfilUseCase {

    public Perfil exec(Long usuarioId);
}
