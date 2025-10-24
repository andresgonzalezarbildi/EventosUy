package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logica.clases.Categoria;

public class CategoriaTest {
    
    private Categoria categoria;
    
    @BeforeEach
    void setUp() {
        categoria = new Categoria("Test Categoria");
    }
    
    @Test
    void testConstructor() {
        assertNotNull(categoria);
        assertEquals("Test Categoria", categoria.getNombre());
    }
    
    @Test
    void testGetNombre() {
        assertEquals("Test Categoria", categoria.getNombre());
    }
    
    
    @Test
    void testConstructorConEmptyString() {
        Categoria cat = new Categoria("");
        assertEquals("", cat.getNombre());
    }
    
    @Test
    void testConstructorConNull() {
        Categoria cat = new Categoria(null);
        assertNull(cat.getNombre());
    }
} 