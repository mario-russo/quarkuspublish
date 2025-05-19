package com.br.mariorusso.infra.entity;

import java.time.LocalDateTime;

import com.br.mariorusso.core.model.Comentario;
import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.model.Usuario;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    public String conteudo;

    @Column(nullable = false)
    public LocalDateTime dataComentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    public UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
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
        Usuario user = new Usuario();
        user.setId(this.usuario.id);
        user.setNome(this.usuario.nome);

        Publicacao p = new Publicacao();
        p.setId(this.publicacao.id);
        p.setUsuario(this.publicacao.usuario.toDomain());
        p.setConteudo(this.conteudo);

        Comentario comentario = new Comentario();
        comentario.setId(this.id);
        comentario.setConteudo(this.conteudo);
        comentario.setDataComentario(this.dataComentario);
        comentario.setPublicacao(p);
        comentario.setUsuario(user);

        return comentario;
    }

}
