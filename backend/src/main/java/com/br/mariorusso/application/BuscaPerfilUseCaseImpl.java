package com.br.mariorusso.application;

import com.br.mariorusso.core.model.Perfil;
import com.br.mariorusso.core.repository.RepositoryCore;
import com.br.mariorusso.core.service.IBuscaPerfilUseCase;
import com.br.mariorusso.interfaces.rest.exception.NotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class BuscaPerfilUseCaseImpl implements IBuscaPerfilUseCase {
    @Inject
    RepositoryCore<Perfil> respository;


    /**
     * Busca um perfil através do identificador do usuário.
     *
     * <p>
     * O método realiza a busca do perfil associado ao usuário informado.
     * </p>
     *
     * <ul>
     *     <li>
     *         Caso exista um perfil vinculado ao usuário,
     *         o perfil será retornado.
     *     </li>
     *     <li>
     *         Caso não exista perfil associado ao usuário informado,
     *         será lançada uma {@link NotFoundException}.
     *     </li>
     * </ul>
     *
     * @param usuarioId identificador do usuário
     * @return perfil associado ao usuário
     * @throws NotFoundException caso o perfil não seja encontrado
     */
    @Override
    public Perfil exec(Long usuarioId) {
        Perfil perfil = respository.findById(usuarioId);
        return perfil;
    }
}
