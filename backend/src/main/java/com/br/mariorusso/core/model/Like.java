package com.br.mariorusso.core.model;

import java.time.LocalDateTime;

public class Like {

    private Long id;
    private LocalDateTime  dataLike;
    private Usuario usuario;
    private Publicacao publicacao;

    public Like() {
    }

    public Like(Long id, LocalDateTime  dataLike, Usuario usuario, Publicacao publicacao) {
        this.id = id;
        this.dataLike = dataLike;
        this.usuario = usuario;
        this.publicacao = publicacao;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
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
        Like other = (Like) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
