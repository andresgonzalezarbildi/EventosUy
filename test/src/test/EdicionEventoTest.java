package src.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logica.clases.EdicionEvento;
import logica.clases.Organizador;
import logica.clases.TipoRegistro;
import logica.clases.Patrocinio;
import logica.clases.Asistente;
import logica.clases.Registro;
import logica.clases.Nivel;
import logica.datatypes.DataTipoRegistro;
import logica.datatypes.DataPatrocinio;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EdicionEventoTest {
    
    private EdicionEvento edicion;
    private LocalDate fechaIni;
    private LocalDate fechaFin;
    
    @BeforeEach
    void setUp() {
        fechaIni = LocalDate.now();
        fechaFin = LocalDate.now().plusDays(1);
        edicion = new EdicionEvento("Test Edition", fechaIni, fechaFin, "Test City", "Test Country", "TE", LocalDate.now());
    }
    
    @Test
    void testConstructor() {
        assertNotNull(edicion);
        assertEquals("Test Edition", edicion.getNombre());
        assertEquals(fechaIni, edicion.getFechaIni());
        assertEquals(fechaFin, edicion.getFechaFin());
        assertEquals("Test City", edicion.getCiudad());
        assertEquals("Test Country", edicion.getPais());
        assertEquals("TE", edicion.getSigla());
        assertNotNull(edicion.getFechaAltaEnPlataforma());
        assertNotNull(edicion.getCategorias());
        assertNotNull(edicion.getPatrocinios());
        assertNotNull(edicion.getRegistros());
    }
    
    @Test
    void testGetNombre() {
        assertEquals("Test Edition", edicion.getNombre());
    }
    
    @Test
    void testGetFechaIni() {
        assertEquals(fechaIni, edicion.getFechaIni());
    }
    
    @Test
    void testGetFechaFin() {
        assertEquals(fechaIni, edicion.getFechaIni());
    }
    
    @Test
    void testGetCiudad() {
        assertEquals("Test City", edicion.getCiudad());
    }
    
    @Test
    void testGetPais() {
        assertEquals("Test Country", edicion.getPais());
    }
    
    @Test
    void testGetSigla() {
        assertEquals("TE", edicion.getSigla());
    }
    
    @Test
    void testGetFechaAltaEnPlataforma() {
        assertNotNull(edicion.getFechaAltaEnPlataforma());
        assertTrue(edicion.getFechaAltaEnPlataforma().isBefore(LocalDate.now().plusDays(1)));
    }
    
    @Test
    void testGetCategorias() {
        Map<String, TipoRegistro> categorias = edicion.getCategorias();
        assertNotNull(categorias);
        assertTrue(categorias.isEmpty());
    }
    
    @Test
    void testAgregarTipoDeRegistro() {
        TipoRegistro tipo = new TipoRegistro("Test Tipo", "Test Description", 100, 50);
        edicion.agregarTipoDeRegistro("Test Tipo", tipo);
        
        assertTrue(edicion.existeTipoDeRegistro("Test Tipo"));
        assertEquals(tipo, edicion.getTipoDeRegistro("Test Tipo"));
    }
    
    @Test
    void testAgregarTipoDeRegistroDuplicado() {
        TipoRegistro tipo1 = new TipoRegistro("Test Tipo", "Test Description 1", 100, 50);
        TipoRegistro tipo2 = new TipoRegistro("Test Tipo", "Test Description 2", 200, 100);
        
        edicion.agregarTipoDeRegistro("Test Tipo", tipo1);
        edicion.agregarTipoDeRegistro("Test Tipo", tipo2);
        
        // Solo debe mantener el primero
        assertEquals(tipo1, edicion.getTipoDeRegistro("Test Tipo"));
    }
    
    @Test
    void testExisteTipoDeRegistro() {
        assertFalse(edicion.existeTipoDeRegistro("Test Tipo"));
        
        TipoRegistro tipo = new TipoRegistro("Test Tipo", "Test Description", 100, 50);
        edicion.agregarTipoDeRegistro("Test Tipo", tipo);
        
        assertTrue(edicion.existeTipoDeRegistro("Test Tipo"));
    }
    
    @Test
    void testGetTipoDeRegistro() {
        TipoRegistro tipo = new TipoRegistro("Test Tipo", "Test Description", 100, 50);
        edicion.agregarTipoDeRegistro("Test Tipo", tipo);
        
        TipoRegistro tipoObtenido = edicion.getTipoDeRegistro("Test Tipo");
        assertNotNull(tipoObtenido);
        assertEquals(tipo, tipoObtenido);
    }
    
    @Test
    void testGetTipoDeRegistroInexistente() {
        TipoRegistro tipoObtenido = edicion.getTipoDeRegistro("Inexistente");
        assertNull(tipoObtenido);
    }
    
    @Test
    void testGetOrganizador() {
        assertNull(edicion.getOrganizador());
        
        Organizador org = new Organizador("testOrg", "Test Organizador", "test@example.com", "Test Description", "http://test.com");
        edicion.setOrganizador(org);
        
        assertEquals(org, edicion.getOrganizador());
    }
    
    @Test
    void testGetPatrocinios() {
        List<Patrocinio> patrocinios = edicion.getPatrocinios();
        assertNotNull(patrocinios);
        assertEquals(2, patrocinios.size()); // Constructor agrega 2 patrocinios por defecto
    }
    
    @Test
    void testAgregarPatrocinio() {
        Patrocinio patrocinio = new Patrocinio(LocalDate.now(), 3000, 15, "PAT003", Nivel.BRONCE);
        edicion.agregarPatrocinio(patrocinio);
        
        List<Patrocinio> patrocinios = edicion.getPatrocinios();
        assertTrue(patrocinios.contains(patrocinio));
    }
    
    @Test
    void testGetOrganizadorDTO() {
        assertEquals("", edicion.getOrganizadorDTO());
        
        Organizador org = new Organizador("testOrg", "Test Organizador", "test@example.com", "Test Description", "http://test.com");
        edicion.setOrganizador(org);
        
        assertEquals("testOrg", edicion.getOrganizadorDTO());
    }
    
    @Test
    void testGetTiposRegistroDTO() {
        TipoRegistro tipo = new TipoRegistro("Test Tipo", "Test Description", 100, 50);
        edicion.agregarTipoDeRegistro("Test Tipo", tipo);
        
        List<DataTipoRegistro> dtos = edicion.getTiposRegistroDTO();
        assertNotNull(dtos);
        assertEquals(1, dtos.size());
        assertEquals("Test Tipo", dtos.get(0).getNombre());
        assertEquals("Test Description", dtos.get(0).getDescripcion());
        assertEquals(100, dtos.get(0).getCosto());
        assertEquals(50, dtos.get(0).getCupo());
    }
    
    @Test
    void testGetPatrociniosDTO() {
        List<DataPatrocinio> dtos = edicion.getPatrociniosDTO();
        assertNotNull(dtos);
        assertEquals(2, dtos.size()); // Constructor agrega 2 patrocinios por defecto
        
        // Verificar que los DTOs tienen la información correcta
        assertNotNull(dtos.get(0).getCodigoDePatrocinio());
        assertNotNull(dtos.get(0).getNivelDePatrocinio());
    }
    
    @Test
    void testSetOrganizador() {
        Organizador org = new Organizador("testOrg", "Test Organizador", "test@example.com", "Test Description", "http://test.com");
        edicion.setOrganizador(org);
        
        assertEquals(org, edicion.getOrganizador());
    }
    
    @Test
    void testHayCupo() {
        TipoRegistro tipo = new TipoRegistro("Test Tipo", "Test Description", 100, 2);
        edicion.agregarTipoDeRegistro("Test Tipo", tipo);
        
        assertTrue(edicion.hayCupo(tipo));
        
        // Agregar 2 registros para llenar el cupo
        Asistente asis1 = new Asistente("asis1", "Asistente 1", "asis1@test.com", "Apellido 1", LocalDate.of(1990, 1, 1));
        Asistente asis2 = new Asistente("asis2", "Asistente 2", "asis2@test.com", "Apellido 2", LocalDate.of(1990, 1, 1));
        
        Registro reg1 = new Registro(LocalDate.now(), 100, tipo, edicion, asis1);
        Registro reg2 = new Registro(LocalDate.now(), 100, tipo, edicion, asis2);
        
        edicion.agregarRegistro(reg1);
        edicion.agregarRegistro(reg2);
        
        assertFalse(edicion.hayCupo(tipo));
    }
    
    @Test
    void testEstaRegistrado() {
        Asistente asis = new Asistente("testAsis", "Test Asistente", "test@test.com", "Test Apellido", LocalDate.of(1990, 1, 1));
        assertFalse(edicion.estaRegistrado(asis));
        
        TipoRegistro tipo = new TipoRegistro("Test Tipo", "Test Description", 100, 50);
        edicion.agregarTipoDeRegistro("Test Tipo", tipo);
        
        Registro reg = new Registro(LocalDate.now(), 100, tipo, edicion, asis);
        edicion.agregarRegistro(reg);
        
        assertTrue(edicion.estaRegistrado(asis));
    }
    
    @Test
    void testAgregarRegistro() {
        Asistente asis = new Asistente("testAsis", "Test Asistente", "test@test.com", "Test Apellido", LocalDate.of(1990, 1, 1));
        TipoRegistro tipo = new TipoRegistro("Test Tipo", "Test Description", 100, 50);
        edicion.agregarTipoDeRegistro("Test Tipo", tipo);
        
        Registro reg = new Registro(LocalDate.now(), 100, tipo, edicion, asis);
        edicion.agregarRegistro(reg);
        
        assertTrue(edicion.estaRegistrado(asis));
        assertEquals(1, edicion.getRegistros().size());
    }
    
    @Test
    void testGetRegistros() {
        assertTrue(edicion.getRegistros().isEmpty());
        
        Asistente asis = new Asistente("testAsis", "Test Asistente", "test@test.com", "Test Apellido", LocalDate.of(1990, 1, 1));
        TipoRegistro tipo = new TipoRegistro("Test Tipo", "Test Description", 100, 50);
        edicion.agregarTipoDeRegistro("Test Tipo", tipo);
        
        Registro reg = new Registro(LocalDate.now(), 100, tipo, edicion, asis);
        edicion.agregarRegistro(reg);
        
        assertEquals(1, edicion.getRegistros().size());
        assertTrue(edicion.getRegistros().contains(reg));
    }
    
    @Test
    void testToString() {
        String str = edicion.toString();
        assertNotNull(str);
        assertTrue(str.contains("Test Edition"));
        assertTrue(str.contains(fechaIni.toString()));
    }
} 