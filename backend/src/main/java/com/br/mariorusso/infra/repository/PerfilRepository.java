package com.br.mariorusso.infra.repository;

import com.br.mariorusso.core.model.Perfil;
import com.br.mariorusso.core.repository.RepositoryCore;
import com.br.mariorusso.infra.entity.PerfilEntity;
import com.br.mariorusso.infra.entity.UsuarioEntity;
import com.br.mariorusso.interfaces.rest.exception.NotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
@ApplicationScoped
public class PerfilRepository implements RepositoryCore<Perfil> {
    @Override
    @Transactional
    public void save(Perfil perfil){

        UsuarioEntity usuario = UsuarioEntity.findById(perfil.getUsuarioId());
        if (usuario == null ){
            throw new NotFoundException("Usuário não encontradado");
        }
        PerfilEntity entity = PerfilEntity.fromDomain(perfil,usuario);
        entity.persist();
    }

    @Override
    @Transactional
    public void update(Perfil perfil) {
    PerfilEntity entity = PerfilEntity.find("usuario.id", perfil.getUsuarioId()).firstResult();

        if (entity == null) {
            throw new NotFoundException("Perfil não encontrado!!!");
        }

        entity.titulo = perfil.getTitulo();
        entity.sobre = perfil.getSobre();
    }

    @Override
    public void delete(Perfil perfil) {
        PerfilEntity entity = PerfilEntity.find("usuario.id", perfil.getUsuarioId()).firstResult();

        if (entity != null) {
            entity.delete();
        } else {
            throw new NotFoundException("Perfil não encontrado!");
        }

    }


    @Override
    public Perfil findById(Long usuarioId) {
        PerfilEntity entity = PerfilEntity.find("usuario.id", usuarioId).firstResult();
        return entity != null ? entity.toDomain() : null;
    }

    @Override
    public List<Perfil> findAll() {
        List<PerfilEntity> entities = PerfilEntity.listAll();

        return entities.stream()
                .map(PerfilEntity::toDomain)
                .toList();
    }

}
