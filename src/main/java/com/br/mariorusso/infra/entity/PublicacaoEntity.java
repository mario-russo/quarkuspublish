package com.br.mariorusso.infra.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.br.mariorusso.core.model.Comentario;
import com.br.mariorusso.core.model.Publicacao;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "publicacao")
public class PublicacaoEntity extends PanacheEntityBase{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    
    @Column(nullable = false)
    private String conteudo;
        
    @Column(nullable = false)
    private LocalDateTime  dataPublicacao;
    
    @Column(nullable = false)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "comentario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "like", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeEntity> likes;

    public static PublicacaoEntity fromDomain(Publicacao publicacao){

        PublicacaoEntity entity= new PublicacaoEntity(); 

        entity.comentarios = publicacao.getComentarios();
        entity.conteudo = publicacao.getConteudo();
        entity.dataPublicacao = publicacao.getDataPublicacao();
        entity.likes = publicacao.getLikes().stream().map(LikeEntity::fromDomain).toList();
        entity.usuario = UsuarioEntity.fromDomain(publicacao.getUsuario());
        entity.id = publicacao.getId();

        return entity;
    }

    public Publicacao toDomain(){
        Publicacao publicacao = new Publicacao();

        publicacao.setComentarios(this.comentarios);
        publicacao.setConteudo(this.conteudo);
        publicacao.setDataPublicacao(this.dataPublicacao);
        publicacao.setId(this.id);
        publicacao.setLikes(this.likes.stream().map(like -> like.toDomain()).toList());
        publicacao.setUsuario(this.usuario.toDomain());
        return publicacao;
    }
    
}
