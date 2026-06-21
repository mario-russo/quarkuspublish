package com.br.mariorusso.domain.model;

import java.time.LocalDateTime;

public class Curtida {

    private Long id;
    private LocalDateTime  dataLike;
    private Long usuarioId;
    private Long publicacaoId;

    public Curtida() {
    }

    public Curtida(Long id, LocalDateTime  dataLike, Long usuario, Long publicacao) {
        this.id = id;
        this.dataLike = dataLike;
        this.usuarioId = usuario;
        this.publicacaoId = publicacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime  getDataLike() {
        return dataLike;
    }

    public void setDataLike(LocalDateTime  dataLike) {
        this.dataLike = dataLike;
    }

    public Long getUsuario() {
        return usuarioId;
    }

    public void setUsuario(Long usuario) {
        this.usuarioId = usuario;
    }

    public Long getPublicacao() {
        return publicacaoId;
    }

    public void setPublicacao(Long publicacao) {
        this.publicacaoId = publicacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Curtida other = (Curtida) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
