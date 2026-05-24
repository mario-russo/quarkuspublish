package com.br.mariorusso.core.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class CurtidaTest {

    @Test
    void testEqualsSameObject() {
        Curtida curtida = new Curtida();
        curtida.setId(1L);
        assertTrue(curtida.equals(curtida)); // mesmo objeto
    }

    @Test
    void testEqualsSameId() {
        Curtida curtida1 = new Curtida();
        curtida1.setId(1L);

        Curtida curtida2 = new Curtida();
        curtida2.setId(1L);

        assertTrue(curtida1.equals(curtida2));
        assertEquals(curtida1.hashCode(), curtida2.hashCode());
    }
    @Test
    void testEqualsDifferentId() {
        Curtida curtida1 = new Curtida();
        curtida1.setId(1L);

        Curtida curtida2 = new Curtida();
        curtida2.setId(2L);

        assertFalse(curtida1.equals(curtida2));
    }

    @Test
    void testEqualsNullId() {
        Curtida curtida1 = new Curtida();
        curtida1.setId(null);

        Curtida curtida2 = new Curtida();
        curtida2.setId(1L);

        assertFalse(curtida1.equals(curtida2));
    }

    @Test
    void testEqualsNullObject() {
        Curtida curtida = new Curtida();
        curtida.setId(1L);

        assertFalse(curtida.equals(null));
    }

    @Test
    void testEqualsDifferentClass() {
        Curtida curtida = new Curtida();
        curtida.setId(1L);

        String notLike = "teste";
        assertFalse(curtida.equals(notLike));
    }

    @Test
    void constructor(){
        Long id = 1L;

        Publicacao publicacao = new Publicacao();
        publicacao.setId(1L);

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        LocalDateTime now = LocalDateTime.now();
        Curtida curtida = new Curtida(1L, now, usuario.getId(), publicacao.getId());

        assertEquals(id, curtida.getId());
        assertEquals(now, curtida.getDataLike());
        assertEquals(usuario.getId(), curtida.getUsuario());
        assertEquals(publicacao.getId(), curtida.getPublicacao());
    }
}