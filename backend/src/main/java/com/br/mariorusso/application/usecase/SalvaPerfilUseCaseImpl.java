package com.br.mariorusso.application.usecase;

import com.br.mariorusso.domain.model.Perfil;
import com.br.mariorusso.application.ports.out.RepositoryCore;
import com.br.mariorusso.application.ports.in.ISalvaPerfilUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SalvaPerfilUseCaseImpl implements ISalvaPerfilUseCase {
    @Inject
    RepositoryCore<Perfil> repositoryCore;

    /**
     * Executa a operação de criação ou atualização de perfil.
     *
     * <p>
     * O fluxo funciona como um "upsert":
     * </p>
     *
     * <ul>
     *     <li>
     *         Caso o usuário ainda não possua um perfil cadastrado,
     *         um novo perfil será criado.
     *     </li>
     *     <li>
     *         Caso já exista um perfil associado ao usuário,
     *         os dados do perfil serão atualizados.
     *     </li>
     * </ul>
     *
     * @param perfil dados do perfil que será criado ou atualizado
     */
    @Override
    public void execute(Perfil perfil) {
        Perfil byId = repositoryCore.findById(perfil.getUsuarioId());

        if (byId == null){
            repositoryCore.save(perfil);
            return;
        }
        repositoryCore.update(perfil);
    }
}
