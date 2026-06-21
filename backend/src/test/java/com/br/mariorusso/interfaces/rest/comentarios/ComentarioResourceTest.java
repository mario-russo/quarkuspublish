package com.br.mariorusso.interfaces.rest.comentarios;

import com.br.mariorusso.domain.model.Comentario;
import com.br.mariorusso.domain.model.Publicacao;
import com.br.mariorusso.domain.model.Usuario;
import com.br.mariorusso.application.ports.in.ServiceCore;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static io.restassured.RestAssured.given;

@QuarkusTest
class ComentarioResourceTest {
    @InjectMock
    ServiceCore<Comentario> comentarioService;

    @InjectMock
    ServiceCore<Publicacao> publicacaoService;

    @InjectMock
    ServiceCore<Usuario> usuarioService;


    @Test
    @DisplayName("salva comentário")
    @TestSecurity(user = "test", roles = {"USER"})
    void salvaComentrio() {
        // Dados de teste
        Long usuarioId = 1L;
        Long publicacaoId = 1L;
        String conteudo = "Ótimo post!";

//

        // Chamar a API e verificar
        given()
                .body("{\"usuario_id\": " + usuarioId + ", " +
                        "\"publicacao_id\": " + publicacaoId + ", " +
                        "\"conteudo\": \"" + conteudo + "\"}")
                .contentType("application/json")
                .when()
                .post("/comentarios")
                .then()
                .statusCode(200)
                .body(equalTo("comentario salvo"));
    }

    @Test
    void buscaTodosComentarios() {
        Comentario comentario1 = new Comentario();
        comentario1.setId(1L);
        comentario1.setConteudo("Primeiro comentário");

        Comentario comentario2 = new Comentario();
        comentario2.setId(2L);
        comentario2.setConteudo("Segundo comentário");

        Mockito.when(comentarioService.findAll()).thenReturn(List.of(comentario1, comentario2));

        given()
                .when()
                .get("/comentarios")
                .then()
                .statusCode(200)
                .body("size()", is(2))
                .body("[0].id", equalTo(1))
                .body("[0].conteudo", equalTo("Primeiro comentário"))
                .body("[1].id", equalTo(2))
                .body("[1].conteudo", equalTo("Segundo comentário"));
    }

    @Test
    void buscacomentarioPorId() {
        Long comentarioId = 1L;
        Comentario comentario = new Comentario();
        comentario.setId(comentarioId);
        comentario.setConteudo("Comentário específico");

        Mockito.when(comentarioService.findById(comentarioId)).thenReturn(comentario);

        given()
                .pathParam("id", comentarioId)
                .when()
                .get("/comentarios/{id}")
                .then()
                .statusCode(200);

    }
}

