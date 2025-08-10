package com.br.mariorusso.interfaces.rest.publicacao;

import com.br.mariorusso.PostgresManagerTest;

import com.br.mariorusso.auth.JwtService;
import com.br.mariorusso.infra.entity.UsuarioEntity;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import io.restassured.http.ContentType;
import jakarta.inject.Inject;
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

    String token;


    @Test
    @TestTransaction
    @DisplayName("salva uma publicação com sucesso")
    void salva_publicacao_com_sucesso() {


        UsuarioEntity byId = UsuarioEntity.findById(1);
        token = jwt.generateToken(byId.toDomain());


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

        given()
                .when()
                .get("/publicacao")
                .then()
                .statusCode(200)
                .body("[0].conteudo", equalTo("Conteúdo inicial"))
                .body("[0].usuario_id", equalTo(1))
                .body("[2].conteudo", equalTo("conteudo admin"));

    }


    @Test
    @DisplayName("Dado id, deleta publicação com sucesso ")
    void deleta_publicacao_pelo_id() {

        //deleta publicação
        given()
                .pathParam("id", 3)
                .when()
                .delete("/publicacao/{id}")
                .then()
                .statusCode(200);

    }


    @Test
    @DisplayName("Dado ID que não esteja no banco de dados erro 404 para deletar")
    void dado_id_invalido_nenhuma_publicacao_encontrada() {
        given()
                .pathParam("id", 100)
                .when()
                .delete("/publicacao/{id}")
                .then()
                .statusCode(404)
                .body(is("Nenhuma publicação encontrada"));
    }

    @Test
    @DisplayName("dado um id valido no banco returna uma publicaçao")
    void busca_publicacao_por_id_status_200() {

        Long id = 1L;

        given()
                .pathParam("id", id)
                .when()
                .get("/publicacao/{id}")
                .then()
                .statusCode(200)
                .body("conteudo", equalTo("Conteúdo inicial"));

    }
    @Test
    void dado_id_invalido_retorna_404(){
        Long id = 100L;

        given()
                .pathParam("id", id)
                .when()
                .get("/publicacao/{id}")
                .then()
                .statusCode(404)
                .body(equalTo("Nenhuma publicação encontrada"));
    }

}