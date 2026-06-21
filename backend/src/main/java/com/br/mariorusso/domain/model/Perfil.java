package com.br.mariorusso.domain.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.Objects;

@RegisterForReflection
public class Perfil {
    private Long id ;
    private String titulo;
    private String sobre;
    private Long usuarioId;

    public Perfil() {
    }

    public Perfil(Long id, String titulo, String sobre, Long usuariId) {
        this.id = id;
        this.titulo = titulo;
        this.sobre = sobre;
        this.usuarioId = usuariId;
    }

    public Long getId() {
        return id;
    }

    public Perfil setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public Perfil setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getSobre() {
        return sobre;
    }

    public Perfil setSobre(String sobre) {
        this.sobre = sobre;
        return this;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Perfil setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Perfil perfil = (Perfil) o;
        return Objects.equals(id, perfil.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", sobre='" + sobre + '\'' +
                ", usuario_id=" + usuarioId +
                '}';
    }
}
