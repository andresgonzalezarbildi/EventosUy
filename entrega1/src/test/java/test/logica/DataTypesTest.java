package test.logica;

import org.junit.jupiter.api.Test;
import logica.datatypes.DataUsuario;
import logica.datatypes.DataEvento;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataTipoRegistro;
import logica.datatypes.DataPatrocinio;
import logica.datatypes.DataRegistro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataTypesTest {
    
    @Test
    void testDataUsuario() {
        LocalDate fechaNac = LocalDate.of(1990, 1, 1);
        DataUsuario dataUsuario = new DataUsuario("testUser", "Test User", "test@example.com", "PerfilSinFoto.png", "Asistente");
        
        assertEquals("testUser", dataUsuario.getNickname());
        assertEquals("Test User", dataUsuario.getNombre());
        assertEquals("test@example.com", dataUsuario.getCorreo());
        assertEquals("Asistente", dataUsuario.getTipo());
        
        // Test setters
        dataUsuario.setApellido("Test Apellido");
        dataUsuario.setFechaNacimiento(fechaNac);
        dataUsuario.setDescripcion("Test Description");
        dataUsuario.setLink("http://test.com");
        
        assertEquals("Test Apellido", dataUsuario.getApellido());
        assertEquals(fechaNac, dataUsuario.getFechaNacimiento());
        assertEquals("Test Description", dataUsuario.getDescripcion());
        assertEquals("http://test.com", dataUsuario.getLink());
    }
    
    @Test
    void testDataUsuarioOrganizador() {
        DataUsuario dataUsuario = new DataUsuario("testOrg", "Test Organizador", "test@example.com", "PerfilSinFoto.png", "Organizador");
        
        assertEquals("Organizador", dataUsuario.getTipo());
        dataUsuario.setDescripcion("Test Description");
        dataUsuario.setLink("http://test.com");
        
        assertEquals("Test Description", dataUsuario.getDescripcion());
        assertEquals("http://test.com", dataUsuario.getLink());
    }
    
    @Test
    void testDataEvento() {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        LocalDate fecha = LocalDate.now();
        
        DataEvento dataEvento = new DataEvento("Test Event", "Test Description", "TE", fecha, categorias, "EventoSinFoto.png");
        
        assertEquals("Test Event", dataEvento.getNombre());
        assertEquals("Test Description", dataEvento.getDescripcion());
        assertEquals("TE", dataEvento.getSigla());
        assertEquals(fecha, dataEvento.getFechaAlta());
        assertEquals(categorias, dataEvento.getCategorias());
    }
    
    @Test
    void testDataEventoConCategoriasVacio() {
        List<String> categorias = new ArrayList<>();
        LocalDate fecha = LocalDate.now();
        
        DataEvento dataEvento = new DataEvento("Test Event", "Test Description", "TE", fecha, categorias, "EventoSinFoto.png");
        
        assertNotNull(dataEvento.getCategorias());
        assertTrue(dataEvento.getCategorias().isEmpty());
    }
    
    @Test
    void testDataEventoToString() {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        LocalDate fecha = LocalDate.now();
        
        DataEvento dataEvento = new DataEvento("Test Event", "Test Description", "TE", fecha, categorias, "EventoSinFoto.png");
        
        assertEquals("Test Event", dataEvento.toString());
    }
    
    @Test
    void testDataEventoSetters() {
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        LocalDate fecha = LocalDate.now();
        
        DataEvento dataEvento = new DataEvento("Test Event", "Test Description", "TE", fecha, categorias, "EventoSinFoto.png");
        
        dataEvento.setNombre("New Name");
        dataEvento.setDescripcion("New Description");
        dataEvento.setSigla("NE");
        dataEvento.setFechaAlta(LocalDate.of(2024, 1, 1));
        
        assertEquals("New Name", dataEvento.getNombre());
        assertEquals("New Description", dataEvento.getDescripcion());
        assertEquals("NE", dataEvento.getSigla());
        assertEquals(LocalDate.of(2024, 1, 1), dataEvento.getFechaAlta());
    }
    
    @Test
    void testDataEdicion() {
        LocalDate fechaIni = LocalDate.now();
        LocalDate fechaFin = LocalDate.now().plusDays(1);
        LocalDate fechaAlta = LocalDate.now();
        List<DataTipoRegistro> tiposRegistro = new ArrayList<>();
        List<DataPatrocinio> patrocinios = new ArrayList<>();
        
        DataEdicion dataEdicion = new DataEdicion("Test Edition", fechaIni, fechaFin, "Test City", 
                                                 "Test Country", "TE", fechaAlta, "testOrg", tiposRegistro, patrocinios, "EdicionSinFoto.png", "ABIERTA", "Test Event");
        
        assertEquals("Test Edition", dataEdicion.getNombre());
        assertEquals(fechaIni, dataEdicion.getFechaIni());
        assertEquals(fechaFin, dataEdicion.getFechaFin());
        assertEquals("Test City", dataEdicion.getCiudad());
        assertEquals("Test Country", dataEdicion.getPais());
        assertEquals("TE", dataEdicion.getSigla());
        assertEquals(fechaAlta, dataEdicion.getFechaAltaEnPlataforma());
        assertEquals("testOrg", dataEdicion.getOrganizador());
        assertEquals(tiposRegistro, dataEdicion.getTiposRegistro());
        assertEquals(patrocinios, dataEdicion.getPatrocinios());
    }
    
    @Test
    void testDataTipoRegistro() {
        DataTipoRegistro dataTipoRegistro = new DataTipoRegistro("Test Tipo", "Test Description", 100, 50);
        
        assertEquals("Test Tipo", dataTipoRegistro.getNombre());
        assertEquals("Test Description", dataTipoRegistro.getDescripcion());
        assertEquals(100, dataTipoRegistro.getCosto());
        assertEquals(50, dataTipoRegistro.getCupo());
    }
    
    @Test
    void testDataTipoRegistroCostoCero() {
        DataTipoRegistro dataTipoRegistro = new DataTipoRegistro("Test Tipo", "Test Description", 0, 50);
        
        assertEquals(0, dataTipoRegistro.getCosto());
    }
    
    @Test
    void testDataPatrocinio() {
        LocalDate fecha = LocalDate.now();
        DataPatrocinio dataPatrocinio = new DataPatrocinio(fecha, 5000, 10, "PAT001", logica.clases.Nivel.ORO);
        
        assertEquals(fecha, dataPatrocinio.getFechaDeRealizacion());
        assertEquals(5000, dataPatrocinio.getMontoAportado());
        assertEquals(10, dataPatrocinio.getCantRegistrosGratis());
        assertEquals("PAT001", dataPatrocinio.getCodigoDePatrocinio());
        assertEquals(logica.clases.Nivel.ORO, dataPatrocinio.getNivelDePatrocinio());
    }
    
    @Test
    void testDataPatrocinioMontoCero() {
        LocalDate fecha = LocalDate.now();
        DataPatrocinio dataPatrocinio = new DataPatrocinio(fecha, 0, 0, "PAT001", logica.clases.Nivel.BRONCE);
        
        assertEquals(0, dataPatrocinio.getMontoAportado());
        assertEquals(0, dataPatrocinio.getCantRegistrosGratis());
    }
    
    @Test
    void testDataPatrocinioToString() {
        LocalDate fecha = LocalDate.now();
        DataPatrocinio dataPatrocinio = new DataPatrocinio(fecha, 5000, 10, "PAT001", logica.clases.Nivel.ORO);
        
        String str = dataPatrocinio.toString();
        assertTrue(str.contains("PAT001"));
        assertTrue(str.contains("ORO"));
    }
    
    @Test
    void testDataRegistro() {
        LocalDate fecha = LocalDate.now();
        DataRegistro dataRegistro = new DataRegistro("Test Event", "Test Edition", "Test Tipo", 100, fecha, "Test Asistente");
        
        assertEquals("Test Event", dataRegistro.getEvento());
        assertEquals("Test Edition", dataRegistro.getEdicion());
        assertEquals("Test Tipo", dataRegistro.getTipoRegistro());
        assertEquals(100, dataRegistro.getCosto());
        assertEquals(fecha, dataRegistro.getFecha());
        assertEquals("Test Asistente", dataRegistro.getAsistente());
    }
    
    @Test
    void testDataRegistroCostoCero() {
        LocalDate fecha = LocalDate.now();
        DataRegistro dataRegistro = new DataRegistro("Test Event", "Test Edition", "Test Tipo", 0, fecha, "Test Asistente");
        
        assertEquals(0, dataRegistro.getCosto());
    }
    
    @Test
    void testDataRegistroCostoNull() {
        LocalDate fecha = LocalDate.now();
        DataRegistro dataRegistro = new DataRegistro("Test Event", "Test Edition", "Test Tipo", null, fecha, "Test Asistente");
        
        assertNull(dataRegistro.getCosto());
    }
    
    @Test
    void testDataTypesNullValues() {
        DataUsuario dataUsuario = new DataUsuario(null, null, null, null, null);
        
        assertNull(dataUsuario.getNickname());
        assertNull(dataUsuario.getNombre());
        assertNull(dataUsuario.getCorreo());
        assertNull(dataUsuario.getTipo());
    }
    
    @Test
    void testDataTypesEmptyStrings() {
        DataUsuario dataUsuario = new DataUsuario("", "", "", "", "");
        
        assertEquals("", dataUsuario.getNickname());
        assertEquals("", dataUsuario.getNombre());
        assertEquals("", dataUsuario.getCorreo());
        assertEquals("", dataUsuario.getTipo());
    }
} 