package com.br.mariorusso.interfaces.rest.usuario;

import com.br.mariorusso.PostgresManagerTest;
import com.br.mariorusso.application.Login;
import com.br.mariorusso.auth.Roles;
import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.repository.RepositoryCore;

import com.br.mariorusso.infra.entity.UsuarioEntity;
import com.br.mariorusso.interfaces.rest.login.TokenFactory;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@QuarkusTest
@QuarkusTestResource(PostgresManagerTest.class)
class UsuarioResourceTest {


    public static final String EMAIL = "mario@gmail.com";
    public static final String NOME = "mario";
    public static final String SENHA = "123456";


    @Inject
    RepositoryCore<Usuario> repositoryCore;

    @Inject
    Login login;

    String token;

    @Test
    @TestSecurity(user = "admin", roles = {"ADMIN"})
    void dado_usuario_valido_retorna_status_200() {

        UsuarioDtos dto = new UsuarioDtos(NOME, EMAIL, SENHA);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/usuario")
                .then()
                .statusCode(200)
                .body(equalTo("Usuario salvo com sucesso"));
    }

    @Test
    void atualiza_usuario_pelo_id() {
        Usuario usuario = new Usuario(null, NOME, "email@gmail.com", SENHA);
        usuario.setRoles(Roles.USER);
        repositoryCore.save(usuario);

        UsuarioEntity user = login.login("email@gmail.com", SENHA);
        token = TokenFactory.of(user.toDomain());


        UsuarioDtos dto = new UsuarioDtos("Atualizado", EMAIL, SENHA);


        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .patch("/usuario")
                .then()
                .log().all()
                .statusCode(204);

    }

    @Test
    void list_usuario_pelo_id() {

        given()
                .pathParam("id", 2)
                .when()
                .get("/usuario/{id}")
                .then()
                .statusCode(200);
    }
    @Test
    void retorna_erro_dado_id_invalido() {

        given()
                .pathParam("id", 100)
                .when()
                .get("/usuario/{id}")
                .then()
                .statusCode(404);
    }


    @Test
    @TestSecurity(user = "mario russo", roles = "ADMIN")
    void list_todos_usuarios() {
        given()
                .when()
                .get("/usuario")
                .then()
                .statusCode(200);

    }

    @Test
    @TestSecurity(user = "mario russo", roles = {"ADMIN"})
    void dado_id_usuario_e_deletado_com_sucesso() {

        given()
                .pathParam("id", 1L)
                .when()
                .delete("/usuario/{id}")
                .then()
                .statusCode(200)
                .body(equalTo("Usuario deletado com sucesso"));

    }
    @Test
    @TestSecurity(user = "mario russo", roles = {"ADMIN"})
    void dado_id_usuario_invalido_erro_404() {

        given()
                .pathParam("id", 100)
                .when()
                .delete("/usuario/{id}")
                .then()
                .statusCode(404)
                .body(equalTo("Usuário não encontrado"));
    }
}