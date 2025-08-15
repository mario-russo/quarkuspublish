package com.br.mariorusso.core.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UsuarioTest {
    @Test
    void testEqualsAndHashCode() {
        Usuario u1 = new Usuario();
        Usuario u2 = new Usuario();

        // Mesmo objeto
        assertTrue(u1.equals(u1));

        // Objeto null
        assertFalse(u1.equals(null));

        // Classe diferente
        assertFalse(u1.equals("teste"));

        // Ambos id null → true
        assertTrue(u1.equals(u2));

        // Um id null → false
        u2.setId(1L);
        assertFalse(u1.equals(u2));

        // Ids diferentes → false
        u1.setId(2L);
        assertFalse(u1.equals(u2));

        // Ids iguais → true e hashCode igual
        u1.setId(1L);
        assertTrue(u1.equals(u2));
        assertEquals(u1.hashCode(), u2.hashCode());
    }
}