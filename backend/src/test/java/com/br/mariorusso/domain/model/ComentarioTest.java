package com.br.mariorusso.core.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class ComentarioTest {

    @Test
    void testEqualsAndHashCode() {
        Comentario c1 = new Comentario();
        Comentario c2 = new Comentario();

        // Mesmo objeto
        assertTrue(c1.equals(c1));

        // Objeto null
        assertFalse(c1.equals(null));

        // Classe diferente
        assertFalse(c1.equals("teste"));

        // Ambos id null → true
        assertTrue(c1.equals(c2));

        // Um id null → false
        c2.setId(1L);
        assertFalse(c1.equals(c2));

        // Ids diferentes → false
        c1.setId(2L);
        assertFalse(c1.equals(c2));

        // Ids iguais → true e hashCode igual
        c1.setId(1L);
        assertTrue(c1.equals(c2));
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}