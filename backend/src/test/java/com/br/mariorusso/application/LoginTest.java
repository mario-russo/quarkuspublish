package com.br.mariorusso.application;

import com.br.mariorusso.auth.Roles;
import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.interfaces.rest.login.RegisterDto;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@QuarkusTest
class LoginTest {

    @Inject
    Login useCase;

    @InjectMock
    UsuarioUseCase usuarioUseCase;


    @Test
    void register() {
        // Dados para o teste
        String nome = "mario russo";
        String email = "mario@gmail.com";
        String senha = "1234";

        RegisterDto dto = new RegisterDto(nome, email, senha);

        // Executa o método que estamos testando
        useCase.register(dto);

        // Captura o objeto Usuario que foi passado para save()
        ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioUseCase, times(1)).save(captor.capture());

        Usuario savedUser = captor.getValue();

        // Verifica se os dados do usuário são iguais ao que esperávamos
        assertEquals(nome, savedUser.getNome());
        assertEquals(email, savedUser.getEmail());
        assertEquals(senha, savedUser.getSenha());

        // Verifica se o papel USER foi atribuído
        assertTrue(savedUser.getRoles().contains(Roles.USER));
    }
}