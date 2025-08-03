package com.br.mariorusso.interfaces.rest.publicacao;

import com.br.mariorusso.PostgresManagerTest;

import com.br.mariorusso.auth.JwtService;
import com.br.mariorusso.auth.Roles;
import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.service.ServiceCore;
import com.br.mariorusso.interfaces.rest.usuario.UserFactoryTest;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;




import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
@QuarkusTestResource(PostgresManagerTest.class)
class PublicacaoResourceTest {
    @Inject
    JwtService jwt;

    @Inject
    ServiceCore<Usuario> usuarioService;

    String token;

    @BeforeEach
    public void init() {
        Usuario usuario = UserFactoryTest.usuario();
        usuarioService.save(usuario);

    }

    @Test
    @TestTransaction
    @DisplayName("salva uma publicação com sucesso")
    void salva_publicacao_com_sucesso() {


        Usuario byId = UserFactoryTest.loginUser();
        byId.setRoles(Roles.USER);
        token = jwt.generateToken(byId);


        var dto = new PublicacaDtoIn("novo conteudo", 1L);
        given()
                .body(dto)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .post("/publicacao")
                .then()
                .statusCode(200)
                .body(equalTo("Publicação salva com sucesso!!!"));

    }

    @Test
    @DisplayName("buca todos as Publicação")
    void lista_todas_publicacao() {
        Usuario byId = UserFactoryTest.loginUser();
        byId.setRoles(Roles.USER);
        token = jwt.generateToken(byId);


        var dto = new PublicacaDtoIn("novo conteudo", 1L);
        given()
                .body(dto)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .post("/publicacao")
                .then()
                .statusCode(200)
                .body(equalTo("Publicação salva com sucesso!!!"));

        given()
                .when()
                .get("/publicacao")
                .then()
                .statusCode(200)
                .body("[0].conteudo", equalTo("novo conteudo"))
                .body("[0].usuario_id", equalTo(1));

    }

    @Test
    @DisplayName("Caso lista publicação está vázia status code 404 ")
    void dado_id_nenhuma_publicacao_encontrada() {
        given()
                .pathParam("id", 1)
                .when()
                .delete("/publicacao/{id}")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/publicacao")
                .then()
                .statusCode(404)
                .body(equalTo("Nenhuma Publicação encontrada"));

    }

    @Test
    @DisplayName("Dado id, deleta publicação com sucesso ")
    void deleta_publicacao_pelo_id() {
        //salva publicação
        Usuario byId = UserFactoryTest.loginUser();
        byId.setRoles(Roles.USER);
        token = jwt.generateToken(byId);


        var dto = new PublicacaDtoIn("novo conteudo", 1L);
        given()
                .body(dto)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .post("/publicacao")
                .then()
                .statusCode(200)
                .body(equalTo("Publicação salva com sucesso!!!"));
        //deleta publicação
        given()
                .pathParam("id", 2)
                .when()
                .delete("/publicacao/{id}")
                .then()
                .statusCode(200);

    }


    @Test
    @DisplayName("Dado ID que não esteja no banco de dados erro 404")
    void dado_id_deleta_Publiccação() {
        given()
                .pathParam("id", 100)
                .when()
                .delete("/publicacao/{id}")
                .then()
                .statusCode(404)
                .body(is("Nenhuma publicação encontrada"));
    }

    @Test
    void dado_id_nenhuma_publicação_encontrada() {

        given()
                .pathParam("id", 100)
                .when()
                .get("/publicacao/{id}")
                .then()
                .statusCode(404)
                .body(is("Nenhuma publicação encontrada"));
    }



}