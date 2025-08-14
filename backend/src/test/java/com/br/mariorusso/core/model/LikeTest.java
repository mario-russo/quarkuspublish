package com.br.mariorusso.core.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class LikeTest {

    @Test
    void testEqualsSameObject() {
        Like like = new Like();
        like.setId(1L);
        assertTrue(like.equals(like)); // mesmo objeto
    }

    @Test
    void testEqualsSameId() {
        Like like1 = new Like();
        like1.setId(1L);

        Like like2 = new Like();
        like2.setId(1L);

        assertTrue(like1.equals(like2));
        assertEquals(like1.hashCode(), like2.hashCode());
    }
    @Test
    void testEqualsDifferentId() {
        Like like1 = new Like();
        like1.setId(1L);

        Like like2 = new Like();
        like2.setId(2L);

        assertFalse(like1.equals(like2));
    }

    @Test
    void testEqualsNullId() {
        Like like1 = new Like();
        like1.setId(null);

        Like like2 = new Like();
        like2.setId(1L);

        assertFalse(like1.equals(like2));
    }

    @Test
    void testEqualsNullObject() {
        Like like = new Like();
        like.setId(1L);

        assertFalse(like.equals(null));
    }

    @Test
    void testEqualsDifferentClass() {
        Like like = new Like();
        like.setId(1L);

        String notLike = "teste";
        assertFalse(like.equals(notLike));
    }

    @Test
    void constructor(){
        Long id = 1L;

        Publicacao publicacao = new Publicacao();
        publicacao.setId(1L);

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        LocalDateTime now = LocalDateTime.now();
        Like like = new Like(1L, now, usuario, publicacao);

        assertEquals(id, like.getId());
        assertEquals(now, like.getDataLike());
        assertEquals(usuario.getId(), like.getUsuario().getId());
        assertEquals(publicacao.getId(), like.getPublicacao().getId());
    }
}