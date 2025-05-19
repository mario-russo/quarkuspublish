package com.br.mariorusso.infra.repository;

import java.util.List;

import com.br.mariorusso.core.model.Comentario;
import com.br.mariorusso.core.repository.RepositoryCore;
import com.br.mariorusso.infra.entity.ComentarioEntity;
import com.br.mariorusso.infra.entity.PublicacaoEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ComentarioRepository implements RepositoryCore<Comentario> {

    @Override
    @Transactional
    public void save(Comentario object) {

        ComentarioEntity fromDomain = ComentarioEntity.fromDomain(object);
        fromDomain.persist();
    }

    @Override
    @Transactional
    public void update(Comentario object) {
        ComentarioEntity comentario = ComentarioEntity.findById(object.getId());
        if(comentario == null){
            throw new IllegalArgumentException("Publicação não encontrado");
        }
        comentario.conteudo =object.getConteudo();
        comentario.dataComentario = object.getDataComentario();
        comentario.publicacao = PublicacaoEntity.fromDomain(object.getPublicacao());
        comentario.persist();
    }

    @Override
    @Transactional
    public void delete(Comentario object) {
        ComentarioEntity comentario = ComentarioEntity.findById(object.getId());
        if(comentario == null){
            throw new IllegalArgumentException("Publicação não encontrado");
        }
        comentario.delete();
    }

    @Override
    public Comentario findById(Long id) {
        ComentarioEntity comentario = ComentarioEntity.findById(id);
        return comentario.toDomain();
    }

    @Override
    public List<Comentario> findAll() {
        List<ComentarioEntity> comentarios = ComentarioEntity.listAll();
        List<Comentario> objeto = comentarios.stream().map(ComentarioEntity::toDomain).toList();
        return objeto;
    }
    
}
