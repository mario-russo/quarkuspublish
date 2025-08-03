package com.br.mariorusso.core.model;

import com.br.mariorusso.auth.Roles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Set<Roles> roles = new HashSet<>();

    private List<Publicacao> posts;
    private List<Comentario> comentarios;
    private List<Like> likes;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Publicacao> getPosts() {
        return posts;
    }

    public void setPosts(List<Publicacao> posts) {
        this.posts = posts;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
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
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Set<Roles> getRoles() {
        return roles;
    }
    public void setRoles(Roles roles) {
        this.roles.add(roles);
    }
    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
