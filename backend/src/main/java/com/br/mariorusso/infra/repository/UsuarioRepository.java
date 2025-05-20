package com.br.mariorusso.infra.repository;

import java.util.List;

import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.repository.RepositoryCore;
import com.br.mariorusso.infra.entity.UsuarioEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioRepository implements RepositoryCore<Usuario> { 

    @Override
    @Transactional
    public void save(Usuario object) {

        UsuarioEntity usuarioEntity = UsuarioEntity.fromDomain(object);
        usuarioEntity.persist();

    }

    @Override
    @Transactional
    public void update(Usuario object) {

        UsuarioEntity usuarioUpDate = UsuarioEntity.findById(object.getId());
        if (usuarioUpDate == null) {
            throw new IllegalArgumentException("Usuario não encontrado");
        }
        usuarioUpDate.nome = object.getNome();
        usuarioUpDate.email = object.getEmail();
        usuarioUpDate.senha = object.getSenha();
        usuarioUpDate.persist();
    }

    @Override
    @Transactional
    public void delete(Usuario object) {
        UsuarioEntity usuarioDelete = UsuarioEntity.findById(object.getId());
        if (usuarioDelete == null) {
            throw new IllegalArgumentException("Usuario não encontrado");
        }
        usuarioDelete.delete();
    }

    @Override
    public Usuario findById(Long id) {
        UsuarioEntity usuario = UsuarioEntity.findById(id);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario não encontrado");
        }
        return usuario.toDomain();
    }

    @Override
    public List<Usuario> findAll() {
        List<UsuarioEntity> usuarios = UsuarioEntity.listAll();
        if (usuarios.isEmpty()) {
            throw new IllegalArgumentException("Nenhum usuario encontrado");
        }
        return usuarios.stream().map(UsuarioEntity::toDomain).toList();
    }

}
