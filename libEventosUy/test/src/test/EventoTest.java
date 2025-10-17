package src.test;

// import static org.junit.jupiter.api.Assertions.*;
import logica.clases.Evento;
import logica.clases.Categoria;
import logica.clases.EdicionEvento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class EventoTest {
    
    private Evento evento;
    private Categoria categoria;
    private EdicionEvento edicion;
    
    @BeforeEach
    void setUp() {
        evento = new Evento("Test Event", "Test Description", "TE", LocalDate.now(), "EventoSinFoto.png");
        categoria = new Categoria("Test Category");
        edicion = new EdicionEvento("Test Edition", LocalDate.now(), LocalDate.now().plusDays(1), 
                                   "Test City", "Test Country", "TE", LocalDate.now());
    }
    
    @Test
    void testConstructor() {
        assertNotNull(evento);
        assertEquals("Test Event", evento.getNombre());
        assertEquals("Test Description", evento.getDescripcionEvento());
        assertEquals("TE", evento.getSigla());
        assertNotNull(evento.getFecha());
        assertNotNull(evento.getCategorias());
        assertNotNull(evento.getEdiciones());
    }
    
    @Test
    void testGetFecha() {
        assertNotNull(evento.getFecha());
        assertTrue(evento.getFecha().isBefore(LocalDate.now().plusDays(1)));
    }
    
    @Test
    void testGetSigla() {
        assertEquals("TE", evento.getSigla());
    }
    
    @Test
    void testGetDescripcionEvento() {
        assertEquals("Test Description", evento.getDescripcionEvento());
    }
    
    @Test
    void testSetFecha() {
        LocalDate nuevaFecha = LocalDate.of(2024, 1, 1);
        evento.setFecha(nuevaFecha);
        assertEquals(nuevaFecha, evento.getFecha());
    }
    
    @Test
    void testGetNombre() {
        assertEquals("Test Event", evento.getNombre());
    }
    
    @Test
    void testGetCategorias() {
        Map<String, Categoria> categorias = evento.getCategorias();
        assertNotNull(categorias);
        assertTrue(categorias.isEmpty());
    }
    
    @Test
    void testAgregarCategoria() {
        evento.agregarCategoria(categoria);
        assertTrue(evento.getCategorias().containsKey("Test Category"));
        assertEquals(categoria, evento.getCategorias().get("Test Category"));
    }
    
    @Test
    void testGetCategoriasLista() {
        evento.agregarCategoria(categoria);
        List<String> categoriasLista = evento.getCategoriasLista();
        assertNotNull(categoriasLista);
        assertEquals(1, categoriasLista.size());
        assertTrue(categoriasLista.contains("Test Category"));
    }
    
    @Test
    void testGetEdiciones() {
        Map<String, EdicionEvento> ediciones = evento.getEdiciones();
        assertNotNull(ediciones);
        assertTrue(ediciones.isEmpty());
    }
    
    @Test
    void testGetEdicion() {
        evento.agregarEdicion(edicion);
        EdicionEvento edicionObtenida = evento.getEdicion("Test Edition");
        assertNotNull(edicionObtenida);
        assertEquals(edicion, edicionObtenida);
    }
    
    @Test
    void testGetEdicionInexistente() {
        EdicionEvento edicionObtenida = evento.getEdicion("Inexistente");
        assertNull(edicionObtenida);
    }
    
    @Test
    void testAgregarEdicion() {
        evento.agregarEdicion(edicion);
        assertTrue(evento.getEdiciones().containsKey("Test Edition"));
        assertEquals(edicion, evento.getEdiciones().get("Test Edition"));
    }
    
    @Test
    void testConstructorWithNullValues() {
        Evento eventoNull = new Evento(null, null, null, null, null);
        assertNull(eventoNull.getNombre());
        assertNull(eventoNull.getDescripcionEvento());
        assertNull(eventoNull.getSigla());
    }
} 