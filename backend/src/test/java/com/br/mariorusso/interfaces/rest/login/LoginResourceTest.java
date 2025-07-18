package com.br.mariorusso.interfaces.rest.login;


import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.service.LoginCore;
import com.br.mariorusso.core.service.ServiceCore;
import com.br.mariorusso.infra.entity.UsuarioEntity;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class LoginResourceTest {
    @Inject
    ServiceCore<Usuario> serviceCore;
    @Inject
    LoginCore<UsuarioEntity> loginUsuario;

    final String EMAIL= "mario@gmail.com";
    final String SENHA = "123456";

    @BeforeEach
    void setUp(){
        Usuario usuario = new Usuario(null, "mario",EMAIL,SENHA);
        serviceCore.save(usuario);
    }

    @Test
    @DisplayName("Dado um usuario uma senha ou email invalido returna status 404")
    void erroAoLogaComUsuarioinvalido() {
        LoginDtoIn dto = new LoginDtoIn("senha","senha");
        given().
                contentType("application/json")
                .body(dto)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(404);

    }
        @Test
    @DisplayName("Dado um usuario válido, status 200")
    void loginComSucesso() {

            RegisterDto dto2 = new RegisterDto("mario",EMAIL,SENHA);
            given()
                    .contentType(ContentType.JSON)
                    .body(dto2)
                    .when()
                    .post("/auth/register")
                    .then()
                    .statusCode(200);

            LoginDtoIn dto = new LoginDtoIn(EMAIL,SENHA);
            given()
                    .contentType(ContentType.JSON)
                    .body(dto)
                    .when()
                    .post("/auth/login")
                    .then()
                    .log().all()
                    .statusCode(200);
    }
    @Test
    @DisplayName("Cadastra um usuário , status 200")
    void retornaStatus200AposDados(){
        RegisterDto dto = new RegisterDto("mario","mario@e.com","123456");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/auth/register")
                .then()
                .statusCode(200);


    }
}