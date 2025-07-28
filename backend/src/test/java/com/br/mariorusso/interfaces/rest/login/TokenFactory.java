package com.br.mariorusso.interfaces.rest.login;

import com.br.mariorusso.auth.Roles;
import com.br.mariorusso.core.model.Usuario;
import io.smallrye.jwt.build.Jwt;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class TokenFactory {

    public static String of(Usuario usuario){

            List<String> roles = usuario.getRoles().stream()
                    .map(Roles::name)
                    .toList();

            return Jwt.issuer("https://github.com/mario-russo")
                    .subject(usuario.getEmail())
                    .groups(Set.copyOf(roles))
                    .claim("id", usuario.getId())
                    .expiresIn(Duration.ofDays(7))
                    .sign();
    }
}
