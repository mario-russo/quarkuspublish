package com.br.mariorusso.infra.entity;

import java.time.LocalDateTime;

import com.br.mariorusso.core.model.Comentario;

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
@Table(name = "comentario")
public class ComentarioEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    private String conteudo;

    @Column(nullable = false)
    public LocalDateTime dataComentario;

    @ManyToOne()
    @JoinColumn(name = "usuario_id", nullable = false)
    public UsuarioEntity usuario;

    @ManyToOne()
    @JoinColumn(name = "publicacao_id", nullable = false)
    public PublicacaoEntity publicacao;

    public static ComentarioEntity fromDomain(Comentario comentario) {
        ComentarioEntity entity = new ComentarioEntity();

        entity.conteudo = comentario.getConteudo();
        entity.dataComentario = comentario.getDataComentario();
        entity.id = comentario.getId();
        entity.publicacao = PublicacaoEntity.fromDomain(comentario.getPublicacao());
        entity.usuario = UsuarioEntity.fromDomain(comentario.getUsuario());

        return entity;
    }

    public Comentario toDomain() {
        Comentario comentario = new Comentario();

        comentario.setConteudo(conteudo);
        comentario.setDataComentario(dataComentario);
        comentario.setId(id);
        comentario.setPublicacao(publicacao.toDomain());
        comentario.setUsuario(usuario.toDomain());
        return comentario;
    }

}
