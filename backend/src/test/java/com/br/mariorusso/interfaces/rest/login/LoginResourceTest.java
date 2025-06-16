package com.br.mariorusso.interfaces.rest.login;

import com.br.mariorusso.application.UsuarioUseCase;
import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.service.ServiceCore;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class LoginResourceTest {
    @Inject
    ServiceCore<Usuario> serviceCore = new UsuarioUseCase();

    @Test
    @DisplayName("Dado um usuario uma senha ou email invalido returna status 404")
    void erroAoLogaComUsuarioinvalido() {
        LoginDtoIn dto = new LoginDtoIn("senha","senha");
        given().
                contentType("application/json")
                .body(dto)
                .when()
                .post("/login")
                .then()
                .statusCode(404);

    }

    @Test
    @DisplayName("Dado um usuario válido, status 200")
    void loginComSucesso() {

        String email= "mario@gmail.com";
        String senha = "1234";
        Usuario usuario = new Usuario(null, "mario",email,senha);
        serviceCore.save(usuario);

        LoginDtoIn dto = new LoginDtoIn(email,senha);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/login")
                .then()
                .statusCode(200);
    }
    @Test
    @DisplayName("Cadastra um usuário , status 200")
    void retornaStatus200AposDado(){
        RegisterDto dto = new RegisterDto("mario","mario@e.com","123456");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/login/register")
                .then()
                .statusCode(200);


    }
}