package com.br.mariorusso.infra.entity;

import java.util.List;

import com.br.mariorusso.core.model.Comentario;
import com.br.mariorusso.core.model.Like;
import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.model.Usuario;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(nullable = false)
    public String nome;
    @Column(nullable = false)
    public String email;
    @Column(nullable = false)
    public String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval =
    true)
    public List<Publicacao> publicacaos;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval =
    true)
    public List<Comentario> comentarios;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval =
    true)
    public List<Like> likes;

    public static UsuarioEntity fromDomain(Usuario usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.id = usuario.getId();
        usuarioEntity.nome=usuario.getNome();
        usuarioEntity.email=usuario.getEmail() ;
        usuarioEntity.senha=usuario.getSenha();
        return usuarioEntity;
    }

    public  Usuario toDomain() {
        Usuario usuario = new Usuario();
        usuario.setId(this.id);
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        return usuario;
    }

}
