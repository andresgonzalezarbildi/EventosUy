package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.clases.Categoria;
import logica.clases.EdicionEvento;
import logica.clases.Evento;
import logica.manejadores.ManejadorEvento;

public class ManejadorEventoTest {
    
    private ManejadorEvento manejador;
    
    @BeforeEach
    void setUp() {
        manejador = ManejadorEvento.getInstance();
        manejador.getEventos().clear();
    }
    
    @Test
    void testGetInstance() {
        ManejadorEvento instancia1 = ManejadorEvento.getInstance();
        ManejadorEvento instancia2 = ManejadorEvento.getInstance();
        assertSame(instancia1, instancia2);
    }
    
    @Test
    void testAgregarEvento() {
        Evento evento = new Evento("Test Event", "Test Description", "TE", LocalDate.now());
        manejador.agregarEvento(evento);
        
        assertTrue(manejador.existeEvento("Test Event"));
        assertEquals(evento, manejador.obtenerEvento("Test Event"));
    }
    
    @Test
    void testObtenerEvento() {
        Evento evento = new Evento("Test Event", "Test Description", "TE", LocalDate.now());
        manejador.agregarEvento(evento);
        
        Evento eventoObtenido = manejador.obtenerEvento("Test Event");
        assertNotNull(eventoObtenido);
        assertEquals("Test Event", eventoObtenido.getNombre());
    }
    
    @Test
    void testObtenerEventoInexistente() {
        Evento eventoObtenido = manejador.obtenerEvento("Inexistente");
        assertNull(eventoObtenido);
    }
    
    @Test
    void testEliminarEvento() {
        Evento evento = new Evento("Test Event", "Test Description", "TE", LocalDate.now());
        manejador.agregarEvento(evento);
        
        assertTrue(manejador.existeEvento("Test Event"));
        
        manejador.eliminarEvento("Test Event");
        assertFalse(manejador.existeEvento("Test Event"));
    }
    
    @Test
    void testGetEventos() {
        Map<String, Evento> eventos = manejador.getEventos();
        assertNotNull(eventos);
        assertTrue(eventos.isEmpty());
        
        Evento evento = new Evento("Test Event", "Test Description", "TE", LocalDate.now());
        manejador.agregarEvento(evento);
        
        assertEquals(1, eventos.size());
        assertTrue(eventos.containsKey("Test Event"));
    }
    
    @Test
    void testGetEventosDTO() {
        Evento evento = new Evento("Test Event", "Test Description", "TE", LocalDate.now());
        manejador.agregarEvento(evento);
        
        var eventosDTO = manejador.getEventosDTO();
        assertNotNull(eventosDTO);
        assertEquals(1, eventosDTO.length);
        assertEquals("Test Event", eventosDTO[0].getNombre());
    }
    
    @Test
    void testExisteEvento() {
        assertFalse(manejador.existeEvento("Test Event"));
        
        Evento evento = new Evento("Test Event", "Test Description", "TE", LocalDate.now());
        manejador.agregarEvento(evento);
        
        assertTrue(manejador.existeEvento("Test Event"));
    }
    
    @Test
    void testAgregarCategoria() {
        Categoria categoria = new Categoria("Test Category");
        manejador.agregarCategoria(categoria);
        
        assertTrue(manejador.existeCategoria("Test Category"));
        assertEquals(categoria, manejador.obtenerCategoria("Test Category"));
    }
    
    @Test
    void testObtenerCategoria() {
        Categoria categoria = new Categoria("Test Category");
        manejador.agregarCategoria(categoria);
        
        Categoria categoriaObtenida = manejador.obtenerCategoria("Test Category");
        assertNotNull(categoriaObtenida);
        assertEquals("Test Category", categoriaObtenida.getNombre());
    }
    
    @Test
    void testObtenerCategoriaInexistente() {
        Categoria categoriaObtenida = manejador.obtenerCategoria("Inexistente");
        assertNull(categoriaObtenida);
    }
    
    @Test
    void testGetCategorias() {
        List<Categoria> categorias = manejador.getCategorias();
        assertNotNull(categorias);
        assertTrue(categorias.isEmpty());
        
        Categoria categoria = new Categoria("Test Category");
        manejador.agregarCategoria(categoria);
        
        assertEquals(1, categorias.size());
        assertTrue(categorias.contains(categoria));
    }
    
    @Test
    void testGetNombresCategorias() {
        List<String> nombresCategorias = manejador.getNombresCategorias();
        assertNotNull(nombresCategorias);
        assertTrue(nombresCategorias.isEmpty());
        
        Categoria categoria = new Categoria("Test Category");
        manejador.agregarCategoria(categoria);
        
        assertEquals(1, nombresCategorias.size());
        assertTrue(nombresCategorias.contains("Test Category"));
    }
    
    @Test
    void testGetCategoriasArray() {
        Categoria[] categoriasArray = manejador.getCategoriasArray();
        assertNotNull(categoriasArray);
        assertEquals(0, categoriasArray.length);
        
        Categoria categoria = new Categoria("Test Category");
        manejador.agregarCategoria(categoria);
        
        categoriasArray = manejador.getCategoriasArray();
        assertEquals(1, categoriasArray.length);
        assertEquals("Test Category", categoriasArray[0].getNombre());
    }
    
    @Test
    void testExisteCategoria() {
        assertFalse(manejador.existeCategoria("Test Category"));
        
        Categoria categoria = new Categoria("Test Category");
        manejador.agregarCategoria(categoria);
        
        assertTrue(manejador.existeCategoria("Test Category"));
    }
    
    @Test
    void testGetEdicion() {
        Evento evento = new Evento("Test Event", "Test Description", "TE", LocalDate.now());
        EdicionEvento edicion = new EdicionEvento("Test Edition", LocalDate.now(), LocalDate.now().plusDays(1), 
                                                 "Test City", "Test Country", "TE", LocalDate.now());
        evento.agregarEdicion(edicion);
        manejador.agregarEvento(evento);
        
        EdicionEvento edicionObtenida = manejador.getEdicion("Test Edition");
        assertNotNull(edicionObtenida);
        assertEquals("Test Edition", edicionObtenida.getNombre());
    }
    
    @Test
    void testGetEdicionInexistente() {
        EdicionEvento edicionObtenida = manejador.getEdicion("Inexistente");
        assertNull(edicionObtenida);
    }
    
    @Test
    void testExisteEdicion() {
        assertFalse(manejador.existeEdicion("Test Edition"));
        
        Evento evento = new Evento("Test Event", "Test Description", "TE", LocalDate.now());
        EdicionEvento edicion = new EdicionEvento("Test Edition", LocalDate.now(), LocalDate.now().plusDays(1), 
                                                 "Test City", "Test Country", "TE", LocalDate.now());
        evento.agregarEdicion(edicion);
        manejador.agregarEvento(evento);
        
        assertTrue(manejador.existeEdicion("Test Edition"));
    }
    
    @Test
    void testExisteEdicionNull() {
        assertFalse(manejador.existeEdicion(null));
    }

} 