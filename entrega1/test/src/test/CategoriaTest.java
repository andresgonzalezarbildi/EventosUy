package src.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logica.clases.Categoria;

public class CategoriaTest {
    
    private Categoria categoria;
    
    @BeforeEach
    void setUp() {
        categoria = new Categoria("Test Category");
    }
    
    @Test
    void testConstructor() {
        assertNotNull(categoria);
        assertEquals("Test Category", categoria.getNombre());
    }
    
    @Test
    void testGetNombre() {
        assertEquals("Test Category", categoria.getNombre());
    }
    
    @Test
    void testToString() {
        assertEquals("Test Category", categoria.toString());
    }
    
    @Test
    void testConstructorWithEmptyString() {
        Categoria cat = new Categoria("");
        assertEquals("", cat.getNombre());
    }
    
    @Test
    void testConstructorWithNull() {
        Categoria cat = new Categoria(null);
        assertNull(cat.getNombre());
    }
} 