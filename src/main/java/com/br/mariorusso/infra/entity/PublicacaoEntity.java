package com.br.mariorusso.infra.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.br.mariorusso.core.model.Publicacao;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "publicacao")
public class PublicacaoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(nullable = false)
    private String conteudo;

    @Column(nullable = false)
    private LocalDateTime dataPublicacao;

    @ManyToOne()
    @JoinColumn(name = "usuario", nullable = false)
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioEntity> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeEntity> likes = new ArrayList<>();

    public static PublicacaoEntity fromDomain(Publicacao publicacao) {

        PublicacaoEntity entity = new PublicacaoEntity();

        entity.id = publicacao.getId();
        entity.conteudo = publicacao.getConteudo();
        entity.dataPublicacao = publicacao.getDataPublicacao();
        entity.usuario = UsuarioEntity.fromDomain(publicacao.getUsuario());
        entity.likes = publicacao.getLikes().stream().map(LikeEntity::fromDomain).toList();
        entity.comentarios = publicacao.getComentarios().stream().map(ComentarioEntity::fromDomain).toList();

        return entity;
    }

    public Publicacao toDomain() {
        Publicacao publicacao = new Publicacao();

        publicacao.setComentarios(this.comentarios.stream().map(like -> like.toDomain()).toList());
        publicacao.setConteudo(this.conteudo);
        publicacao.setDataPublicacao(this.dataPublicacao);
        publicacao.setId(this.id);
        publicacao.setLikes(this.likes.stream().map(like -> like.toDomain()).toList());
        publicacao.setUsuario(this.usuario.toDomain());
        return publicacao;
    }

}
