package com.br.mariorusso.application.auth;

import com.br.mariorusso.domain.model.Usuario;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.Duration;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class JwtService {

    @ConfigProperty(name = "mp.jwt.verify.issuer", defaultValue = "mario-russo")
    String issuer;

    public String generateToken(Usuario usuario) {
        List<String> roles = usuario.getRoles().stream()
                .map(Roles::name)
                .toList();

        return Jwt.issuer(this.issuer)
                .subject(usuario.getEmail())
                .groups(Set.copyOf(roles))
                .claim("id", usuario.getId())
                .expiresIn(Duration.ofDays(7))
                .sign();
    }
}
