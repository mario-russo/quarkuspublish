package com.br.mariorusso.infra.entity;

import java.time.LocalDateTime;

import com.br.mariorusso.core.model.Curtida;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;


@Entity
@Table(
        name = "curtida",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"usuario_id", "publicacao_id"}
                )
        }
)
public class LikeEntity extends PanacheEntityBase{
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, updatable = false)
    public LocalDateTime criadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    public UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicacao_id", nullable = false)
    public PublicacaoEntity publicacao;

    public static LikeEntity fromDomain(Curtida curtida){
        LikeEntity entity = new LikeEntity();

        entity.id = curtida.getId();
        entity.criadoEm = curtida.getDataLike();

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.id = curtida.getUsuario();

        PublicacaoEntity publicacao = new PublicacaoEntity();
        publicacao.id = curtida.getPublicacao();

        entity.usuario = usuario;
        entity.publicacao = publicacao;

        return entity;
    }

    public Curtida toDomain() {
        Curtida curtida = new Curtida();

        curtida.setId(id);
        curtida.setDataLike(criadoEm);
        curtida.setPublicacao(usuario.id);
        curtida.setUsuario(publicacao.id);


        return curtida;
    }
    @PrePersist
    public void prePersist() {
        criadoEm = LocalDateTime.now();
    }
    public static LikeEntity existsByUsuarioAndPublicacao(Long usuarioId, Long publicacaoId) {

        return find(
                "usuario.id = ?1 and publicacao.id = ?2",
                usuarioId,
                publicacaoId
        ).firstResult();
    }
}
