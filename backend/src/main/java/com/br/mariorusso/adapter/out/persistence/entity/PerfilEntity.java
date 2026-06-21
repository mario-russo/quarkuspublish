package com.br.mariorusso.adapter.out.infra.entity;



import com.br.mariorusso.domain.model.Perfil;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity(name = "perfil")
public class PerfilEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String  titulo;
    public  String sobre ;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    public UsuarioEntity usuario;

    public  static  PerfilEntity fromDomain (Perfil perfil, UsuarioEntity usuario){

//        UsuarioEntity usuario = UsuarioEntity.findById(perfil.getUsuario_id());
        PerfilEntity entity =new PerfilEntity();

        entity.sobre = perfil.getSobre();
        entity.titulo = perfil.getTitulo();
        entity.usuario = usuario;
        return entity;
    }


    public Perfil toDomain(){
           return new Perfil()
                .setSobre(this.sobre)
                .setTitulo(this.titulo)
                 .setUsuarioId(this.usuario.id)
                   .setId(id);
    }
}
