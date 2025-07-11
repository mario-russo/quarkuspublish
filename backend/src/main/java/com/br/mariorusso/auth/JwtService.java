package com.br.mariorusso.auth;

import com.br.mariorusso.core.model.Usuario;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.Duration;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class JwtService {

    
    
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer; // vai conter algo como: file:secrets/private.pem

    @ConfigProperty(name = "private-key")
    private String privateKey;


    public String generateToken(Usuario usuario) {
        List<String> roles = usuario.getRoles().stream()
                .map(Roles::name)
                .toList();

        return Jwt.issuer(this.issuer)
                .subject(usuario.getEmail())
                .groups(Set.copyOf(roles))
                .claim("id", usuario.getId())
                .expiresIn(Duration.ofDays(7))
                .sign(this.privateKey);
    }
}
