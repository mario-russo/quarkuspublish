package com.br.mariorusso.adapter.out.infra.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.br.mariorusso.domain.model.Publicacao;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "publicacao")
public class PublicacaoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String conteudo;

    @Column(nullable = false,name = "data_publicacao")
    public LocalDateTime dataPublicacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    public UsuarioEntity usuario;

    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ComentarioEntity> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public List<LikeEntity> likes = new ArrayList<>();

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
