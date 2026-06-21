package com.br.mariorusso.adapter.in.rest.perfil;


import com.br.mariorusso.domain.model.Perfil;
import com.br.mariorusso.application.ports.in.IBuscaPerfilUseCase;
import com.br.mariorusso.application.ports.in.ISalvaPerfilUseCase;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.ClaimValue;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("perfil")
public class PerfilResource {

    @Inject
    ISalvaPerfilUseCase salvaPerfil;

    @Inject
    IBuscaPerfilUseCase buscaPerfil;

    @Inject
    @Claim("id")
    ClaimValue<Long> clamId;
    @POST
    @RolesAllowed("USER")
    public Response salvaUsuario(PerfilDtoIn dto){

        Perfil perfil = new Perfil()
                .setSobre(dto.sobre())
                .setUsuarioId(clamId.getValue())
                .setTitulo(dto.titulo());
        salvaPerfil.execute(perfil);
        return  Response.ok("Salvo com suceso").build();
    }


    @GET
    @Path("/{id}")
    public Response buscarPorUsuarioId(@PathParam("id") Long id) {
        Perfil perfil = buscaPerfil.exec(id);
        return Response.ok(perfil).build();
    }
    @GET
    @RolesAllowed("USER")
    public Response buscaPerfilUsuario() {

            Perfil perfil = buscaPerfil.exec(clamId.getValue());
            return Response.ok(perfil).build();


    }

}
