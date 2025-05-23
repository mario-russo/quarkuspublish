package com.br.mariorusso.infra.entity;

import java.time.LocalDateTime;

import com.br.mariorusso.core.model.Like;
import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.model.Usuario;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "curtida")
public class LikeEntity extends PanacheEntityBase{
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public LocalDateTime dataLike;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    public UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "publicacao_id", nullable = false)
    public PublicacaoEntity publicacao;

    public static LikeEntity fromDomain(Like like){
        LikeEntity likeEntity = new LikeEntity();

        likeEntity.id = like.getId();
        likeEntity.dataLike = like.getDataLike();
        likeEntity.publicacao = PublicacaoEntity.fromDomain(like.getPublicacao());
        likeEntity.usuario = UsuarioEntity.fromDomain(like.getUsuario());
        return likeEntity;
    }

    public Like toDomain(){
        Usuario user = new Usuario();
        user.setNome(this.usuario.nome); 

        Publicacao p = new Publicacao();
        p.setConteudo(publicacao.conteudo);
        p.setId(publicacao.id);
        p.setUsuario(publicacao.usuario.toDomain());
        
        Like like = new Like();


        like.setDataLike(dataLike);
        like.setId(id);
        like.setPublicacao(p);
        like.setUsuario(user);

        return like;
    }
}
