package src.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logica.controladores.ControladorEvento;
import logica.datatypes.DataEvento;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataTipoRegistro;
import excepciones.CategoriaRepetidaException;
import excepciones.EdicionNoExisteException;
import excepciones.EventoNoExisteException;
import excepciones.UsuarioNoExisteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorEventoTest {
    
    private ControladorEvento controlador;
    
    @BeforeEach
    void setUp() {
        controlador = new ControladorEvento();
    }
    
    @Test
    void testAltaEvento() {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now());
        
        DataEvento[] eventos = controlador.getEventosDTO();
        assertNotNull(eventos);
        assertEquals(1, eventos.length);
        assertEquals("Test Event", eventos[0].getNombre());
    }
    
    @Test
    void testAltaEventoNombreVacio() {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEvento("", "Test Description", "TE", categorias, LocalDate.now());
        });
    }
    
    @Test
    void testAltaEventoSiglaVacia() {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEvento("Test Event", "Test Description", "", categorias, LocalDate.now());
        });
    }
    
    @Test
    void testAltaEventoSinCategorias() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEvento("Test Event", "Test Description", "TE", new ArrayList<>(), LocalDate.now());
        });
    }
    
    @Test
    void testAltaCategoria() throws CategoriaRepetidaException {
        controlador.altaCategoria("Test Category");
        
        // Verificar que se puede crear un evento con esa categoría
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now());
        
        DataEvento[] eventos = controlador.getEventosDTO();
        assertEquals(1, eventos.length);
    }
    
    @Test
    void testAltaCategoriaNombreVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaCategoria("");
        });
    }
    
    @Test
    void testAltaCategoriaRepetida() throws CategoriaRepetidaException {
        controlador.altaCategoria("Test Category");
        
        assertThrows(CategoriaRepetidaException.class, () -> {
            controlador.altaCategoria("Test Category");
        });
    }
    
    @Test
    void testGetEventosDTO() {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        controlador.altaEvento("Test Event 1", "Test Description 1", "TE1", categorias, LocalDate.now());
        controlador.altaEvento("Test Event 2", "Test Description 2", "TE2", categorias, LocalDate.now());
        
        DataEvento[] eventos = controlador.getEventosDTO();
        assertNotNull(eventos);
        assertEquals(2, eventos.length);
    }
    
    @Test
    void testListarEventoExistentes() throws EventoNoExisteException {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now());
        
        DataEvento[] eventos = controlador.listarEventoExistentes();
        assertNotNull(eventos);
        assertEquals(1, eventos.length);
        assertEquals("Test Event", eventos[0].getNombre());
    }
    
    @Test
    void testListarEventoExistentesVacio() {
        assertThrows(EventoNoExisteException.class, () -> {
            controlador.listarEventoExistentes();
        });
    }
    
    @Test
    void testListarEdiciones() throws EventoNoExisteException, UsuarioNoExisteException {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now());
        controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        DataEdicion[] ediciones = controlador.listarEdiciones("Test Event");
        assertNotNull(ediciones);
        assertEquals(1, ediciones.length);
        assertEquals("Test Edition", ediciones[0].getNombre());
    }
    
    @Test
    void testListarEdicionesEventoInexistente() {
        assertThrows(EventoNoExisteException.class, () -> {
            controlador.listarEdiciones("Evento Inexistente");
        });
    }
    
    @Test
    void testAltaEdicionEvento() throws UsuarioNoExisteException {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now());
        controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        // Verificar que se creó la edición
        DataEvento[] eventos = controlador.getEventosDTO();
        assertEquals(1, eventos.length);
    }
    
    @Test
    void testAltaEdicionEventoEventoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEdicionEvento("", "Test Edition", "TE", "Test City", "Test Country", 
                                        LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        });
    }
    
    @Test
    void testAltaEdicionEventoOrganizadorVacio() {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now());
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                        LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "");
        });
    }
    
    @Test
    void testAltaEdicionEventoFechasInvalidas() {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now());
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                        LocalDate.now().plusDays(1), LocalDate.now(), LocalDate.now(), "testOrg");
        });
    }
    
    @Test
    void testAltaTipoRegistro() throws UsuarioNoExisteException {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now());
        controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        controlador.altaTipoRegistro("Test Event", "Test Edition", "Test Tipo", "Test Description", 100, 50);
        
        DataTipoRegistro[] tipos = controlador.listarTiposRegistro("Test Event", "Test Edition");
        assertNotNull(tipos);
        assertEquals(1, tipos.length);
        assertEquals("Test Tipo", tipos[0].getNombre());
    }
    
    @Test
    void testAltaTipoRegistroCostoNegativo() throws UsuarioNoExisteException {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now());
        controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaTipoRegistro("Test Event", "Test Edition", "Test Tipo", "Test Description", -100, 50);
        });
    }
    
    @Test
    void testAltaTipoRegistroCupoInvalido() throws UsuarioNoExisteException {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now());
        controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaTipoRegistro("Test Event", "Test Edition", "Test Tipo", "Test Description", 100, 0);
        });
    }
    
    @Test
    void testGetTipoRegistro() throws UsuarioNoExisteException {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now());
        controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        controlador.altaTipoRegistro("Test Event", "Test Edition", "Test Tipo", "Test Description", 100, 50);
        
        DataTipoRegistro tipo = controlador.getTipoRegistro("Test Event", "Test Edition", "Test Tipo");
        assertNotNull(tipo);
        assertEquals("Test Tipo", tipo.getNombre());
        assertEquals(100, tipo.getCosto());
        assertEquals(50, tipo.getCupo());
    }
} 