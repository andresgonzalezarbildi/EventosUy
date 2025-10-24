package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logica.controladores.ControladorEvento;
import logica.controladores.ControladorUsuario;
import logica.datatypes.DataEvento;
import logica.datatypes.DataRegistro;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataTipoRegistro;
import excepciones.CategoriaRepetidaException;
import excepciones.EdicionNoExisteException;
import excepciones.EventoNoExisteException;
import excepciones.EventoRepetidoException;
import excepciones.TipoRegistroRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorEventoTest {
    
    private ControladorEvento controlador;
    private ControladorUsuario controladorUsuario;
    private List<String> categorias;
    
    private void crearOrganizador() throws UsuarioRepetidoException {
      controladorUsuario.altaUsuario(
        "testOrg", "Roberto", "org@test.com", "PerfilSinFoto.png", "contrasenia",
        "Organizador", "Organizador de eventos", "http://ejemplo.com", null, LocalDate.now()
      );
    }

    private void crearCategoria() throws CategoriaRepetidaException {
      controlador.altaCategoria("Categoria Prueba");
    }
    
    @BeforeEach
    void setUp() {
    	controlador = ControladorEvento.getInstance();
    	controladorUsuario = ControladorUsuario.getInstance(); 
      controlador.limpiar();
    	
    	try {
    	  crearOrganizador();
    	} catch (UsuarioRepetidoException e) {
    	  // ignorar
    	}
    	
    	try {
        crearCategoria();
        categorias = new ArrayList<>();
        categorias.add("Categoria Prueba");
      } catch (CategoriaRepetidaException e) {
        // ignorar
      }
    }
    
    @Test
    void testAltaEvento() {      
        try {
            controlador.altaEvento("Evento Prueba", "Descripción del evento", "EP", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail();
        }
        
        DataEvento[] eventos = controlador.getEventosDTO();
        assertNotNull(eventos);
        assertEquals(1, eventos.length);
        assertEquals("Evento Prueba", eventos[0].getNombre());
    }
    
    @Test
    void testAltaEventoNombreVacio() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEvento("", "Descripción", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        });
    }
    
    @Test
    void testAltaEventoSiglaVacia() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEvento("Evento Prueba", "Descripción", "", categorias, LocalDate.now(), "EventoSinFoto.png");
        });
    }
    
    @Test
    void testAltaEventoSinCategorias() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEvento("Evento Prueba", "Descripción", "EP", new ArrayList<>(), LocalDate.now(), "EventoSinFoto.png");
        });
    }
    
    @Test
    void testAltaCategoria() throws CategoriaRepetidaException {
        controlador.altaCategoria("Categoria Prueba 2");
        
        List<String> categorias2 = new ArrayList<>();
        categorias2.add("Categoria Prueba 2");
        try {
          controlador.altaEvento("Evento Prueba", "Descripción", "EP", categorias2, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
          // No hacer nada
        }
        
        DataEvento[] eventos = controlador.getEventosDTO();
        assertEquals(1, eventos.length);
        String categoria = eventos[0].getCategorias().getFirst();
        assertEquals("Categoria Prueba 2", categoria);
    }
    
    @Test
    void testAltaCategoriaNombreVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaCategoria("");
        });
    }
    
    @Test
    void testAltaCategoriaRepetida() throws CategoriaRepetidaException {       
        assertThrows(CategoriaRepetidaException.class, () -> {
            controlador.altaCategoria("Categoria Prueba");
        });
    }
    
    @Test
    void testGetEventosDTO() {       
        try {
            controlador.altaEvento("Evento Prueba 1", "Descripción del evento 1", "EP1", categorias, LocalDate.now(), "EventoSinFoto.png");
            controlador.altaEvento("Evento Prueba 2", "Descripción del evento 2", "EP2", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail();
        }
        
        DataEvento[] eventos = controlador.getEventosDTO();
        assertNotNull(eventos);
        assertEquals(2, eventos.length);
    }
    
    @Test
    void testListarEventoExistentes() throws EventoNoExisteException {
        
        try {
            controlador.altaEvento("Evento Prueba", "Descripción del evento", "EP", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail();
        }
        
        DataEvento[] eventos = controlador.listarEventoExistentes();
        assertNotNull(eventos);
        assertEquals(1, eventos.length);
        assertEquals("Evento Prueba", eventos[0].getNombre());
    }
    
    @Test
    void testListarEventoExistentesVacio() {
        assertThrows(EventoNoExisteException.class, () -> {
            controlador.listarEventoExistentes();
        });
    }
    
    @Test
    void testListarEdiciones() throws EventoNoExisteException, UsuarioNoExisteException {
        
        try {
            controlador.altaEvento("Evento Prueba", "Descripción del evento", "EP", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail();
        }
        controlador.altaEdicionEvento("Evento Prueba", "Edición Prueba", "EP", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        DataEdicion[] ediciones = controlador.listarEdiciones("Evento Prueba");
        assertNotNull(ediciones);
        assertEquals(1, ediciones.length);
        assertEquals("Edición Prueba", ediciones[0].getNombre());
    }
    
    @Test
    void testListarEdicionesEventoInexistente() {
        assertThrows(EventoNoExisteException.class, () -> {
            controlador.listarEdiciones("Evento Inexistente");
        });
    }
    
    @Test
    void testAltaEdicionEvento() throws UsuarioNoExisteException {
        
        try {
            controlador.altaEvento("Evento Prueba", "Descripción del evento", "EP", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        controlador.altaEdicionEvento("Evento Prueba", "Edición Prueba", "EP", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        DataEvento[] eventos = controlador.getEventosDTO();
        assertEquals(1, eventos.length);
    }
    
    @Test
    void testAltaEdicionEventoEventoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEdicionEvento("", "Edición Prueba", "EP", "Montevideo", "Uruguay", 
                                        LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        });
    }
    
    @Test
    void testAltaEdicionEventoOrganizadorVacio() {
        
        try {
            controlador.altaEvento("Evento Prueba", "Descripción del evento", "EP", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEdicionEvento("Evento Prueba", "Edición Prueba", "EP", "Montevideo", "Uruguay", 
                                        LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "");
        });
    }
    
    @Test
    void testAltaEdicionEventoFechasInvalidas() {
        
        try {
            controlador.altaEvento("Evento Prueba", "Descripción del evento", "EP", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEdicionEvento("Evento Prueba", "Edición Prueba", "EP", "Montevideo", "Uruguay", 
                                        LocalDate.now().plusDays(1), LocalDate.now(), LocalDate.now(), "testOrg");
        });
    }
    
    @Test
    void testAltaTipoRegistro() throws UsuarioNoExisteException {
        
        try {
            controlador.altaEvento("Evento Prueba", "Descripción del evento", "EP", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        controlador.altaEdicionEvento("Evento Prueba", "Edición Prueba", "EP", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        try {
            controlador.altaTipoRegistro("Evento Prueba", "Edición Prueba", "Tipo Registro Prueba", "Descripción del tipo", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        DataTipoRegistro[] tipos = controlador.listarTiposRegistro("Evento Prueba", "Edición Prueba");
        assertNotNull(tipos);
        assertEquals(1, tipos.length);
        assertEquals("Tipo Registro Prueba", tipos[0].getNombre());
    }
    
    @Test
    void testAltaTipoRegistroCostoNegativo() throws UsuarioNoExisteException {
        
        try {
            controlador.altaEvento("Evento Prueba", "Descripción del evento", "EP", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        controlador.altaEdicionEvento("Evento Prueba", "Edición Prueba", "EP", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaTipoRegistro("Evento Prueba", "Edición Prueba", "Tipo Registro Prueba", "Descripción del tipo", -100, 50);
        });
    }
    
    @Test
    void testAltaTipoRegistroCupoInvalido() throws UsuarioNoExisteException {
        
        try {
            controlador.altaEvento("Evento Prueba", "Descripción del evento", "EP", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        controlador.altaEdicionEvento("Evento Prueba", "Edición Prueba", "EP", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaTipoRegistro("Evento Prueba", "Edición Prueba", "Tipo Registro Prueba", "Descripción del tipo", 100, 0);
        });
    }
    
    @Test
    void testGetTipoRegistro() throws UsuarioNoExisteException {
        
        try {
            controlador.altaEvento("Evento Prueba", "Descripción del evento", "EP", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail();
        }
        controlador.altaEdicionEvento("Evento Prueba", "Edición Prueba", "EP", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        try {
            controlador.altaTipoRegistro("Evento Prueba", "Edición Prueba", "Tipo Registro Prueba", "Descripción del tipo", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail();
        }
        
        DataTipoRegistro tipo = controlador.getTipoRegistro("Evento Prueba", "Edición Prueba", "Tipo Registro Prueba");
        assertNotNull(tipo);
        assertEquals("Tipo Registro Prueba", tipo.getNombre());
        assertEquals(100, tipo.getCosto());
        assertEquals(50, tipo.getCupo());
    }
    
    @Test
    void testGetInfoEdicion() throws Exception {
        
        try {
            controlador.altaEvento("Evento Prueba", "Descripción del evento", "EP", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail();
        }
        controlador.altaEdicionEvento("Evento Prueba", "Edición Prueba", "EP", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        DataEdicion edicion = controlador.getInfoEdicion("Edición Prueba");
        assertNotNull(edicion);
        assertEquals("Edición Prueba", edicion.getNombre());
        assertEquals("Montevideo", edicion.getCiudad());
    }
    
    @Test
    void testGetUnEventoDTO() throws Exception {
        
        try {
            controlador.altaEvento("Evento Prueba", "Descripción del evento", "EP", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        DataEvento evento = controlador.getUnEventoDTO("Evento Prueba");
        assertNotNull(evento);
        assertEquals("Evento Prueba", evento.getNombre());
        assertEquals("Descripción del evento", evento.getDescripcion());
    }
    
    @Test
    void testGetUnEventoDTOInexistente() {
        assertThrows(EventoNoExisteException.class, () -> {
            controlador.getUnEventoDTO("Evento Inexistente");
        });
    }
    
    @Test
    void testAltaRegistro() throws Exception {
        try {
            controladorUsuario.altaUsuario(
                "testAsis", "Juan", "asis@test.com", "PerfilSinFoto.png", "contraseniaAsis",
                "Asistente", null, null, "Pérez", LocalDate.of(1990, 1, 1)
            );
        } catch (UsuarioRepetidoException e) {
            // No hacer nada
        }
        
        
        try {
            controlador.altaEvento("Evento Prueba", "Descripción del evento", "EP", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        LocalDate fechaAltaEdi = LocalDate.now();
        LocalDate fechaFinEdi = LocalDate.now().plusDays(10);
        controlador.altaEdicionEvento("Evento Prueba", "Edición Prueba", "EP", "Montevideo", "Uruguay", 
                                    fechaAltaEdi, fechaFinEdi, fechaAltaEdi, "testOrg");
        
        try {
            controlador.altaTipoRegistro("Evento Prueba", "Edición Prueba", "Tipo Registro Prueba", "Descripción del tipo", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail();
        }
        
        LocalDate fechaRegistro = fechaAltaEdi.plusDays(1);
        controlador.altaRegistro("Evento Prueba", "Edición Prueba", "Tipo Registro Prueba", "testAsis", fechaRegistro);
        
        DataRegistro[] registros = controlador.listarRegistrosDeEdicion("Edición Prueba");
        assertNotNull(registros);
        assertEquals(1, registros.length);
        assertEquals("testAsis", registros[0].getAsistente());
    }
    
    @Test
    void testListarRegistrosDeUsuario() throws Exception {
        
        try {
            controladorUsuario.altaUsuario(
                "testAsis2", "María", "asis2@test.com", "PerfilSinFoto.png", "contraseniaAsis",
                "Asistente", null, null, "González", LocalDate.of(1990, 1, 1)
            );
            controladorUsuario.altaUsuario(
                "testAsis3", "Pedro", "asis3@test.com", "PerfilSinFoto.png", "contraseniaAsis",
                "Asistente", null, null, "Rodríguez", LocalDate.of(1990, 1, 1)
            );
        } catch (UsuarioRepetidoException e) {
            // No hacer nada
        }
        
        
        try {
            controlador.altaEvento("Evento Prueba 2", "Descripción del evento", "EP2", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail();
        }
        
        LocalDate fechaAltaEdi = LocalDate.now();
        LocalDate fechaFinEdi = LocalDate.now().plusDays(10);
        controlador.altaEdicionEvento("Evento Prueba 2", "Edición Prueba 2", "EP2", "Montevideo", "Uruguay", 
                                    fechaAltaEdi, fechaFinEdi, fechaAltaEdi, "testOrg");
        
        try {
            controlador.altaTipoRegistro("Evento Prueba 2", "Edición Prueba 2", "Tipo Registro Prueba", "Descripción del tipo", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail();
        }
        
        LocalDate fechaRegistro = fechaAltaEdi.plusDays(1);
        controlador.altaRegistro("Evento Prueba 2", "Edición Prueba 2", "Tipo Registro Prueba", "testAsis2", fechaRegistro);
        controlador.altaRegistro("Evento Prueba 2", "Edición Prueba 2", "Tipo Registro Prueba", "testAsis3", fechaRegistro);
        
        DataRegistro[] registros = controlador.listarRegistrosDeUsuario("testAsis2");
        assertNotNull(registros);
        assertEquals(1, registros.length);
        assertEquals("testAsis2", registros[0].getAsistente());
    }
    
    @Test
    void testListarUnRegistroDeUsuario() throws Exception {     
        try {
            controladorUsuario.altaUsuario(
                "testAsis3", "Pedro", "asis3@test.com", "PerfilSinFoto.png", "contraseniaAsis",
                "Asistente", null, null, "Rodríguez", LocalDate.of(1990, 1, 1)
            );
        } catch (UsuarioRepetidoException e) {
            // No hacer nada
        }
        
        
        try {
            controlador.altaEvento("Evento Prueba 3", "Descripción del evento", "EP3", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        LocalDate fechaAltaEdi = LocalDate.now();
        LocalDate fechaFinEdi = LocalDate.now().plusDays(10);
        controlador.altaEdicionEvento("Evento Prueba 3", "Edición Prueba 3", "EP3", "Montevideo", "Uruguay", 
                                    fechaAltaEdi, fechaFinEdi, fechaAltaEdi, "testOrg");
        
        try {
            controlador.altaTipoRegistro("Evento Prueba 3", "Edición Prueba 3", "Tipo Registro Prueba", "Descripción del tipo", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail();
        }
        
        LocalDate fechaRegistro = fechaAltaEdi.plusDays(1);
        controlador.altaRegistro("Evento Prueba 3", "Edición Prueba 3", "Tipo Registro Prueba", "testAsis3", fechaRegistro);
        
        DataRegistro registro = controlador.listarUnRegistroDeUsuario("Edición Prueba 3", "testAsis3");
        assertNotNull(registro);
        assertEquals("testAsis3", registro.getAsistente());
        assertEquals("Edición Prueba 3", registro.getEdicion());
    }
    
    @Test
    void testAceptarEdicion() throws Exception {
        
        try {
            controlador.altaEvento("Evento Prueba 4", "Descripción del evento", "EP4", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Evento Prueba 4", "Edición Prueba 4", "EP4", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        controlador.aceptarEdicion("Edición Prueba 4", true);
        
        DataEdicion edicion = controlador.getInfoEdicion("Edición Prueba 4");
        assertEquals("aceptada", edicion.getEstado());
    }
    
    @Test
    void testRechazarEdicion() throws Exception {
        
        try {
            controlador.altaEvento("Evento Prueba 5", "Descripción del evento", "EP5", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Evento Prueba 5", "Edición Prueba 5", "EP5", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        controlador.aceptarEdicion("Edición Prueba 5", false);
        
        DataEdicion edicion = controlador.getInfoEdicion("Edición Prueba 5");
        assertEquals("rechazada", edicion.getEstado());
    }
    
    @Test
    void testListarEdicionesOrganizador() throws Exception {
        
        try {
            controlador.altaEvento("Evento Prueba 6", "Descripción del evento", "EP6", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Evento Prueba 6", "Edición Prueba 6", "EP6", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        DataEdicion[] ediciones = controlador.listarEdicionesOrganizador("testOrg");
        assertNotNull(ediciones);
        assertEquals(true, ediciones.length >= 1);
    }
    
    @Test
    void testListarEdicionesAceptadas() throws Exception {
        
        try {
            controlador.altaEvento("Evento Prueba 7", "Descripción del evento", "EP7", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Evento Prueba 7", "Edición Prueba 7", "EP7", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        controlador.aceptarEdicion("Edición Prueba 7", true);
        
        DataEdicion[] ediciones = controlador.listarEdicionesAceptadas();
        assertNotNull(ediciones);
        assertEquals(true, ediciones.length >= 1);
    }
    
    @Test
    void testListarEdicionesAceptadasEvento() throws Exception {
        
        try {
            controlador.altaEvento("Evento Prueba 8", "Descripción del evento", "EP8", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Evento Prueba 8", "Edición Prueba 8", "EP8", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        controlador.aceptarEdicion("Edición Prueba 8", true);
        
        DataEdicion[] ediciones = controlador.listarEdicionesAceptadasEvento("Evento Prueba 8");
        assertNotNull(ediciones);
        assertEquals(1, ediciones.length);
    }
    
    @Test
    void testListarEdicionesIngresadas() throws Exception {
        try {
            controlador.altaEvento("Evento Prueba 9", "Descripción del evento", "EP9", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Evento Prueba 9", "Edición Prueba 9", "EP9", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        DataEdicion[] ediciones = controlador.listarEdicionesIngresadas();
        assertNotNull(ediciones);
        assertEquals(true, ediciones.length >= 1);
    }
    
    @Test
    void testListarEdicionesIngresadasEvento() throws Exception {
        
        try {
            controlador.altaEvento("Evento Prueba 10", "Descripción del evento", "EP10", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Evento Prueba 10", "Edición Prueba 10", "EP10", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        DataEdicion[] ediciones = controlador.listarEdicionesIngresadasEvento("Evento Prueba 10");
        assertNotNull(ediciones);
        assertEquals(1, ediciones.length);
    }
    
    @Test
    void testListarEdicionesRechazadas() throws Exception {
        
        try {
            controlador.altaEvento("Evento Prueba 11", "Descripción del evento", "EP11", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Evento Prueba 11", "Edición Prueba 11", "EP11", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        controlador.aceptarEdicion("Edición Prueba 11", false);
        
        DataEdicion[] ediciones = controlador.listarEdicionesRechazadas();
        assertNotNull(ediciones);
        assertEquals(true, ediciones.length >= 1);
    }
    
    @Test
    void testListarEdicionesRechazadasEvento() throws Exception {
        
        try {
            controlador.altaEvento("Evento Prueba 12", "Descripción del evento", "EP12", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Evento Prueba 12", "Edición Prueba 12", "EP12", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        controlador.aceptarEdicion("Edición Prueba 12", false);
        
        DataEdicion[] ediciones = controlador.listarEdicionesRechazadasEvento("Evento Prueba 12");
        assertNotNull(ediciones);
        assertEquals(1, ediciones.length);
    }
    
    @Test
    void testListarCategorias() throws Exception {
        try {  
            controlador.altaCategoria("Categoria Prueba 1");
            controlador.altaCategoria("Categoria Prueba 2");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        
        List<String> categorias = controlador.listarCategorias();
        assertNotNull(categorias);
        assertEquals(true, categorias.size() >= 2);
    }
    
    @Test
    void testGetEventoDeUnaEdicion() throws Exception {
        
        try {
            controlador.altaEvento("Evento Prueba 13", "Descripción del evento", "EP13", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Evento Prueba 13", "Edición Prueba 13", "EP13", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        String nombreEvento = controlador.getEventoDeUnaEdicion("Edición Prueba 13");
        assertEquals("Evento Prueba 13", nombreEvento);
    }
    
    @Test
    void testSetCostoRegistro() throws Exception {
        
        try {
            controladorUsuario.altaUsuario(
                "testAsis4", "Ana", "asis4@test.com", "PerfilSinFoto.png", "contraseniaAsis",
                "Asistente", null, null, "Martínez", LocalDate.of(1990, 1, 1)
            );
        } catch (UsuarioRepetidoException e) {
            // No hacer nada
        }
        
        
        try {
            controlador.altaEvento("Evento Prueba 14", "Descripción del evento", "EP14", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        LocalDate fechaAltaEdi = LocalDate.now();
        LocalDate fechaFinEdi = LocalDate.now().plusDays(10);
        controlador.altaEdicionEvento("Evento Prueba 14", "Edición Prueba 14", "EP14", "Montevideo", "Uruguay", 
                                    fechaAltaEdi, fechaFinEdi, fechaAltaEdi, "testOrg");
        
        try {
            controlador.altaTipoRegistro("Evento Prueba 14", "Edición Prueba 14", "Tipo Registro Prueba", "Descripción del tipo", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        LocalDate fechaRegistro = fechaAltaEdi.plusDays(1);
        controlador.altaRegistro("Evento Prueba 14", "Edición Prueba 14", "Tipo Registro Prueba", "testAsis4", fechaRegistro);
        
        controlador.setCostoRegistro("testAsis4", "Edición Prueba 14", "Tipo Registro Prueba", 200);
        
        DataRegistro registro = controlador.listarUnRegistroDeUsuario("Edición Prueba 14", "testAsis4");
        assertEquals(200, registro.getCosto());
    }
    
    @Test
    void testListarEdicionesOrganizadorAceptadas() throws Exception {
        
        try {
            controlador.altaEvento("Evento Prueba 15", "Descripción del evento", "EP15", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Evento Prueba 15", "Edición Prueba 15", "EP15", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        controlador.aceptarEdicion("Edición Prueba 15", true);
        
        DataEdicion[] ediciones = controlador.listarEdicionesOrganizadorAceptadas("testOrg");
        assertNotNull(ediciones);
        assertEquals(true, ediciones.length >= 1);
    }
    
    @Test
    void testAltaEdicionEventoConImagen() throws Exception {
        
        try {
            controlador.altaEvento("Evento Prueba 16", "Descripción del evento", "EP16", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Evento Prueba 16", "Edición Prueba 16", "EP16", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg", "imagen.png");
        
        DataEdicion edicion = controlador.getInfoEdicion("Edición Prueba 16");
        assertNotNull(edicion);
        assertEquals("Edición Prueba 16", edicion.getNombre());
    }
    
    @Test
    void testNombreEdicionRepetido() throws Exception {

        
        try {
            controlador.altaEvento("Evento Prueba 17", "Descripción del evento", "EP17", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Evento Prueba 17", "Edición Prueba 17", "EP17", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        assertEquals(true, controlador.nombreEdicionRepetido("Edición Prueba 17"));
        assertEquals(false, controlador.nombreEdicionRepetido("Edición Inexistente"));
    }
    
    @Test
    void testAltaTipoRegistroRepetido() throws Exception {

        
        try {
            controlador.altaEvento("Evento Prueba 18", "Descripción del evento", "EP18", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Evento Prueba 18", "Edición Prueba 18", "EP18", "Montevideo", "Uruguay", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        try {
            controlador.altaTipoRegistro("Evento Prueba 18", "Edición Prueba 18", "Tipo Registro Prueba", "Descripción del tipo", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        assertThrows(TipoRegistroRepetidoException.class, () -> {
            controlador.altaTipoRegistro("Evento Prueba 18", "Edición Prueba 18", "Tipo Registro Prueba", "Otra descripción", 200, 100);
        });
    }
    
    @Test
    void testAltaRegistroEventoInexistente() throws Exception {
        try {
            controladorUsuario.altaUsuario(
                "testAsis5", "Carlos", "asis5@test.com", "PerfilSinFoto.png", "contraseniaAsis",
                "Asistente", null, null, "López", LocalDate.of(1990, 1, 1)
            );
        } catch (UsuarioRepetidoException e) {
            // No hacer nada
        }
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaRegistro("Evento Inexistente", "Edición Inexistente", "Tipo Registro", "testAsis5", LocalDate.now());
        });
    }
    
    @Test
    void testAltaRegistroEdicionInexistente() throws Exception {

        try {
            controladorUsuario.altaUsuario(
                "testAsis6", "Laura", "asis6@test.com", "PerfilSinFoto.png", "contraseniaAsis",
                "Asistente", null, null, "Fernández", LocalDate.of(1990, 1, 1)
            );
        } catch (UsuarioRepetidoException e) {
            // No hacer nada
        }
        
        
        try {
            controlador.altaEvento("Evento Prueba 19", "Descripción del evento", "EP19", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaRegistro("Evento Prueba 19", "Edición Inexistente", "Tipo Registro", "testAsis6", LocalDate.now());
        });
    }
    
    @Test
    void testAltaRegistroAsistenteInexistente() throws Exception {
        
        try {
            controlador.altaEvento("Evento Prueba 20", "Descripción del evento", "EP20", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        LocalDate fechaAltaEdi = LocalDate.now();
        LocalDate fechaFinEdi = LocalDate.now().plusDays(10);
        controlador.altaEdicionEvento("Evento Prueba 20", "Edición Prueba 20", "EP20", "Montevideo", "Uruguay", 
                                    fechaAltaEdi, fechaFinEdi, fechaAltaEdi, "testOrg");
        
        try {
            controlador.altaTipoRegistro("Evento Prueba 20", "Edición Prueba 20", "Tipo Registro Prueba", "Descripción del tipo", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaRegistro("Evento Prueba 20", "Edición Prueba 20", "Tipo Registro Prueba", "AsistenteInexistente", fechaAltaEdi.plusDays(1));
        });
    }
    
    @Test
    void testListarRegistrosDeEdicionVacia() {
        DataRegistro[] registros = controlador.listarRegistrosDeEdicion("Edición Inexistente");
        assertNotNull(registros);
        assertEquals(0, registros.length);
    }
    
    @Test
    void testListarRegistrosDeUsuarioVacio() {
        DataRegistro[] registros = controlador.listarRegistrosDeUsuario("UsuarioInexistente");
        assertNotNull(registros);
        assertEquals(0, registros.length);
    }
    
    @Test
    void testGetInfoEdicionInexistente() {
        assertThrows(EdicionNoExisteException.class, () -> {
            controlador.getInfoEdicion("Edición Inexistente");
        });
    }
    
    @Test
    void testAceptarEdicionInexistente() {
        assertThrows(EdicionNoExisteException.class, () -> {
            controlador.aceptarEdicion("Edición Inexistente", true);
        });
    }
    
    @Test
    void testListarEdicionesOrganizadorSinEdiciones() {
        assertThrows(EdicionNoExisteException.class, () -> {
            controlador.listarEdicionesOrganizador("testOrg");
        });
    }
    
    @Test
    void testListarEdicionesAceptadasSinEdiciones() {
        assertThrows(EdicionNoExisteException.class, () -> {
            controlador.listarEdicionesAceptadas();
        });
    }
    
    @Test
    void testListarEdicionesIngresadasSinEdiciones() {
        assertThrows(EdicionNoExisteException.class, () -> {
            controlador.listarEdicionesIngresadas();
        });
    }
    
    @Test
    void testListarEdicionesRechazadasSinEdiciones() {
        assertThrows(EdicionNoExisteException.class, () -> {
            controlador.listarEdicionesRechazadas();
        });
    }
    
    @Test
    void testAltaEventoRepetido() throws Exception {
        
        try {
            controlador.altaEvento("Evento Repetido", "Descripción del evento", "ER", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        assertThrows(EventoRepetidoException.class, () -> {
            controlador.altaEvento("Evento Repetido", "Otra descripción", "ER2", categorias, LocalDate.now(), "EventoSinFoto.png");
        });
    }
} 