package com.br.mariorusso.core.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PublicacaoTest {
    @Test
    void testEqualsAndHashCode() {
        Publicacao p1 = new Publicacao();
        Publicacao p2 = new Publicacao();

        // Mesmo objeto
        assertTrue(p1.equals(p1));

        // Objeto null
        assertFalse(p1.equals(null));

        // Classe diferente
        assertFalse(p1.equals("teste"));

        // Ambos id null → true
        assertTrue(p1.equals(p2));

        // Um id null → false
        p2.setId(1L);
        assertFalse(p1.equals(p2));

        // Ids diferentes → false
        p1.setId(2L);
        assertFalse(p1.equals(p2));

        // Ids iguais → true e hashCode igual
        p1.setId(1L);
        assertTrue(p1.equals(p2));
        assertEquals(p1.hashCode(), p2.hashCode());
    }
}