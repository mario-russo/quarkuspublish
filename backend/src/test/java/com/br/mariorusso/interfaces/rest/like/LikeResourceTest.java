package com.br.mariorusso.interfaces.rest.like;

import com.br.mariorusso.PostgresManagerTest;
import com.br.mariorusso.auth.JwtService;
import com.br.mariorusso.core.model.Like;
import com.br.mariorusso.core.model.Publicacao;
import com.br.mariorusso.core.model.Usuario;
import com.br.mariorusso.core.service.ServiceCore;

import com.br.mariorusso.infra.entity.UsuarioEntity;


import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
@QuarkusTestResource(PostgresManagerTest.class)
class LikeResourceTest {
    @Inject
    ServiceCore<Usuario> serviceUser;

    @Inject
    ServiceCore<Publicacao> servicePub;

    @Inject
    JwtService jwt;


    @Test
    @DisplayName("Curti post com sucesso")
    @Transactional
    void like_post_sucesso() {
        UsuarioEntity usuarioEntity = UsuarioEntity.findById(2);
        var usuario = usuarioEntity.toDomain();


        String token = jwt.generateToken(usuario);

        LikeDtoIn dto = new LikeDtoIn(1L, 1L, LocalDateTime.now());


        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("/like")
                .then()
                .statusCode(200);

    }

    @Test
    void dado_id_usuario_invalido_erro_ao_salva() {

        assertThrows(IllegalArgumentException.class, () -> {
            Usuario usuario = serviceUser.findById(100L);
            String token = jwt.generateToken(usuario);

            LikeDtoIn dto = new LikeDtoIn(1L, 1L, LocalDateTime.now());

            given()
                    .contentType(ContentType.JSON)
                    .body(dto)
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .post("/like")
                    .then()
                    .statusCode(200);
        });
    }
    @Test
    void dado_id_publicacao_invalido_erro404(){
        Usuario usuario = serviceUser.findById(2L);
        String token = jwt.generateToken(usuario);

        LikeDtoIn dto = new LikeDtoIn(1L, 100L, LocalDateTime.now());

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("/like")
                .then()
                .statusCode(404);
    }

    @Test
    void buscaTodosLikes() {
        List<Like> like = given()
                .when()
                .get("/like")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("", Like.class);
        assertFalse(like.isEmpty());
    }

    @Test
    void buscaLikePorId() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/like/{id}")
                .then()
                .statusCode(200);

    }

    @Test
    void dado_id_invalido_retorna_404() {
        given()
                .pathParam("id", 100)
                .when()
                .get("/like/{id}")
                .then()
                .statusCode(404)
                .body(equalTo("sem curtida"));
    }

}