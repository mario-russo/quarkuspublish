package com.br.mariorusso.core;

import java.time.LocalDateTime;

public class Comentario {

    private Long id;
    private String conteudo;
    private LocalDateTime  dataComentario;
    private Usuario usuario;
    private Publicacao publicacao;

    public Comentario() {
    }

    public Comentario(Long id, String conteudo, LocalDateTime  dataComentario, Usuario usuario, Publicacao publicacao) {
        this.id = id;
        this.conteudo = conteudo;
        this.dataComentario = dataComentario;
        this.usuario = usuario;
        this.publicacao = publicacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime  getDataComentario() {
        return dataComentario;
    }

    public void setDataComentario(LocalDateTime  dataComentario) {
        this.dataComentario = dataComentario;
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
        Comentario other = (Comentario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
