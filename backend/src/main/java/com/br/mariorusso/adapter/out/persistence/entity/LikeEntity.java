package com.br.mariorusso.adapter.out.infra.entity;

import java.time.LocalDateTime;

import com.br.mariorusso.domain.model.Curtida;

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

    @Column(nullable = false, updatable = false,name = "criado_em")
    public LocalDateTime criadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    public UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicacao_id", nullable = false)
    public PublicacaoEntity publicacao;

    public static LikeEntity fromDomain(Curtida curtida){
        LikeEntity entity = new LikeEntity();

        entity.usuario =  UsuarioEntity.findById(curtida.getUsuario());
        entity.publicacao = PublicacaoEntity.findById(curtida.getPublicacao());

        return entity;
    }

    public Curtida toDomain() {
        Curtida curtida = new Curtida();

        curtida.setId(id);
        curtida.setDataLike(criadoEm);
        curtida.setPublicacao(publicacao.id);
        curtida.setUsuario(usuario.id);

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
