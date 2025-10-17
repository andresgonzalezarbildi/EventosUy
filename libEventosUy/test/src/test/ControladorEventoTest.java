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
    
    @BeforeEach
    void setUp() {
    	controlador = ControladorEvento.getInstance();
    	controladorUsuario = ControladorUsuario.getInstance(); 
      controlador.limpiar();
    	
    	try {
	        controladorUsuario.altaUsuario(
	            "testOrg", "Nombre", "org@test.com", "PerfilSinFoto.png", "contrasenia",
	            "Organizador", "descripcion", "http://hola.com", null, LocalDate.now()
	        );
        } catch (UsuarioRepetidoException e) {
            // No hacer anda
        }
    }
    
    @Test
    void testAltaEvento() {
    	try {  
    	    controlador.altaCategoria("Test Categoria");
    	} catch (CategoriaRepetidaException e) {
    	  // No hago nada
    	}
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Categoria");
        
        try {
            controlador.altaEvento("evento", "descripcion", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail();
        }
        
        DataEvento[] eventos = controlador.getEventosDTO();
        assertNotNull(eventos);
        assertEquals(1, eventos.length);
        assertEquals("evento", eventos[0].getNombre());
    }
    
    @Test
    void testAltaEventoNombreVacio() {
    	try {  
    	    controlador.altaCategoria("Test Categoria");
    	} catch (CategoriaRepetidaException e) {
    	    // No hacer nada
    	}
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Categoria");
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEvento("", "Descripcion", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        });
    }
    
    @Test
    void testAltaEventoSiglaVacia() {
    	try {  
    	    controlador.altaCategoria("Test Categoria");
    	} catch (CategoriaRepetidaException e) {
    	    // No hacer nada
    	}
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Categoria");
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEvento("Evento", "Descripcion", "", categorias, LocalDate.now(), "EventoSinFoto.png");
        });
    }
    
    @Test
    void testAltaEventoSinCategorias() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEvento("evento", "descripcion", "TE", new ArrayList<>(), LocalDate.now(), "EventoSinFoto.png");
        });
    }
    
    @Test
    void testAltaCategoria() throws CategoriaRepetidaException {
        controlador.altaCategoria("Test Categoria");
        
        // Verificar que se puede crear un evento con esa categoría
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Categoria");
        try {
          controlador.altaEvento("evento", "descripcion", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
          // No hacer nada
        }
        
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
        controlador.altaCategoria("Test Categoria");
        
        assertThrows(CategoriaRepetidaException.class, () -> {
            controlador.altaCategoria("Test Categoria");
        });
    }
    
    @Test
    void testGetEventosDTO() {
    	try {  
    	    controlador.altaCategoria("Test Categoria");
    	} catch (CategoriaRepetidaException e) {
    	    // No Hace nada
    	}
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Categoria");
        
        try {
            controlador.altaEvento("evento1", "Test Description 1", "TE1", categorias, LocalDate.now(), "EventoSinFoto.png");
            controlador.altaEvento("evento2", "Test Description 2", "TE2", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        DataEvento[] eventos = controlador.getEventosDTO();
        assertNotNull(eventos);
        assertEquals(2, eventos.length);
    }
    
    @Test
    void testListarEventoExistentes() throws EventoNoExisteException {
    	try {  
    	    controlador.altaCategoria("Test Category");
    	} catch (CategoriaRepetidaException e) {
    	    // No Hace nada
    	}
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
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
    	try {  
    	    controlador.altaCategoria("Test Category");
    	} catch (CategoriaRepetidaException e) {
    	    // No Hace nada
    	}
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
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
    	try {  
    	    controlador.altaCategoria("Test Category");
    	} catch (CategoriaRepetidaException e) {
    	    // No Hace nada
    	}
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
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
    	try {  
    	    controlador.altaCategoria("Test Category");
    	} catch (CategoriaRepetidaException e) {
    	    // No Hace nada
    	}
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                        LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "");
        });
    }
    
    @Test
    void testAltaEdicionEventoFechasInvalidas() {
    	try {  
    	    controlador.altaCategoria("Test Category");
    	} catch (CategoriaRepetidaException e) {
    	    // No Hace nada
    	}
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                        LocalDate.now().plusDays(1), LocalDate.now(), LocalDate.now(), "testOrg");
        });
    }
    
    @Test
    void testAltaTipoRegistro() throws UsuarioNoExisteException {
    	try {  
    	    controlador.altaCategoria("Test Category");
    	} catch (CategoriaRepetidaException e) {
    	    // No Hace nada
    	}
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        try {
            controlador.altaTipoRegistro("Test Event", "Test Edition", "Test Tipo", "Test Description", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        DataTipoRegistro[] tipos = controlador.listarTiposRegistro("Test Event", "Test Edition");
        assertNotNull(tipos);
        assertEquals(1, tipos.length);
        assertEquals("Test Tipo", tipos[0].getNombre());
    }
    
    @Test
    void testAltaTipoRegistroCostoNegativo() throws UsuarioNoExisteException {
    	try {  
    	    controlador.altaCategoria("Test Category");
    	} catch (CategoriaRepetidaException e) {
    	    // No Hace nada
    	}
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaTipoRegistro("Test Event", "Test Edition", "Test Tipo", "Test Description", -100, 50);
        });
    }
    
    @Test
    void testAltaTipoRegistroCupoInvalido() throws UsuarioNoExisteException {
    	try {  
    	    controlador.altaCategoria("Test Category");
    	} catch (CategoriaRepetidaException e) {
    	    // No Hace nada
    	}
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaTipoRegistro("Test Event", "Test Edition", "Test Tipo", "Test Description", 100, 0);
        });
    }
    
    @Test
    void testGetTipoRegistro() throws UsuarioNoExisteException {
    	try {  
    	    controlador.altaCategoria("Test Category");
    	} catch (CategoriaRepetidaException e) {
    	    // No Hace nada
    	}
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        try {
            controlador.altaTipoRegistro("Test Event", "Test Edition", "Test Tipo", "Test Description", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        DataTipoRegistro tipo = controlador.getTipoRegistro("Test Event", "Test Edition", "Test Tipo");
        assertNotNull(tipo);
        assertEquals("Test Tipo", tipo.getNombre());
        assertEquals(100, tipo.getCosto());
        assertEquals(50, tipo.getCupo());
    }
    
    @Test
    void testGetInfoEdicion() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        DataEdicion edicion = controlador.getInfoEdicion("Test Edition");
        assertNotNull(edicion);
        assertEquals("Test Edition", edicion.getNombre());
        assertEquals("Test City", edicion.getCiudad());
    }
    
    @Test
    void testGetUnEventoDTO() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        DataEvento evento = controlador.getUnEventoDTO("Test Event");
        assertNotNull(evento);
        assertEquals("Test Event", evento.getNombre());
        assertEquals("Test Description", evento.getDescripcion());
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
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        
        try {
            controladorUsuario.altaUsuario(
                "testAsis", "Asistente Nombre", "asis@test.com", "PerfilSinFoto.png", "contraseniaAsis",
                "Asistente", null, null, "Apellido", LocalDate.of(1990, 1, 1)
            );
        } catch (UsuarioRepetidoException e) {
            // No hacer nada
        }
        
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event", "Test Description", "TE", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        LocalDate fechaAltaEdi = LocalDate.now();
        LocalDate fechaFinEdi = LocalDate.now().plusDays(10);
        controlador.altaEdicionEvento("Test Event", "Test Edition", "TE", "Test City", "Test Country", 
                                    fechaAltaEdi, fechaFinEdi, fechaAltaEdi, "testOrg");
        
        try {
            controlador.altaTipoRegistro("Test Event", "Test Edition", "Test Tipo", "Test Description", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        LocalDate fechaRegistro = fechaAltaEdi.plusDays(1);
        controlador.altaRegistro("Test Event", "Test Edition", "Test Tipo", "testAsis", fechaRegistro);
        
        // Verificar que se creó el registro
        DataRegistro[] registros = controlador.listarRegistrosDeEdicion("Test Edition");
        assertNotNull(registros);
        assertEquals(1, registros.length);
        assertEquals("testAsis", registros[0].getAsistente());
    }
    
    @Test
    void testListarRegistrosDeUsuario() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        
        try {
            controladorUsuario.altaUsuario(
                "testAsis2", "Asistente Nombre", "asis2@test.com", "PerfilSinFoto.png", "contraseniaAsis",
                "Asistente", null, null, "Apellido", LocalDate.of(1990, 1, 1)
            );
            controladorUsuario.altaUsuario(
                "testAsis3", "Asistente Nombre", "asis3@test.com", "PerfilSinFoto.png", "contraseniaAsis",
                "Asistente", null, null, "Apellido", LocalDate.of(1990, 1, 1)
            );
        } catch (UsuarioRepetidoException e) {
            // No hacer nada
        }
        
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event2", "Test Description", "TE2", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        LocalDate fechaAltaEdi = LocalDate.now();
        LocalDate fechaFinEdi = LocalDate.now().plusDays(10);
        controlador.altaEdicionEvento("Test Event2", "Test Edition2", "TE2", "Test City", "Test Country", 
                                    fechaAltaEdi, fechaFinEdi, fechaAltaEdi, "testOrg");
        
        try {
            controlador.altaTipoRegistro("Test Event2", "Test Edition2", "Test Tipo", "Test Description", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        LocalDate fechaRegistro = fechaAltaEdi.plusDays(1);
        controlador.altaRegistro("Test Event2", "Test Edition2", "Test Tipo", "testAsis2", fechaRegistro);
        controlador.altaRegistro("Test Event2", "Test Edition2", "Test Tipo", "testAsis3", fechaRegistro);
        
        DataRegistro[] registros = controlador.listarRegistrosDeUsuario("testAsis2");
        assertNotNull(registros);
        assertEquals(1, registros.length);
        assertEquals("testAsis2", registros[0].getAsistente());
    }
    
    @Test
    void testListarUnRegistroDeUsuario() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        
        try {
            controladorUsuario.altaUsuario(
                "testAsis3", "Asistente Nombre", "asis3@test.com", "PerfilSinFoto.png", "contraseniaAsis",
                "Asistente", null, null, "Apellido", LocalDate.of(1990, 1, 1)
            );
        } catch (UsuarioRepetidoException e) {
            // No hacer nada
        }
        
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event3", "Test Description", "TE3", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        LocalDate fechaAltaEdi = LocalDate.now();
        LocalDate fechaFinEdi = LocalDate.now().plusDays(10);
        controlador.altaEdicionEvento("Test Event3", "Test Edition3", "TE3", "Test City", "Test Country", 
                                    fechaAltaEdi, fechaFinEdi, fechaAltaEdi, "testOrg");
        
        try {
            controlador.altaTipoRegistro("Test Event3", "Test Edition3", "Test Tipo", "Test Description", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        LocalDate fechaRegistro = fechaAltaEdi.plusDays(1);
        controlador.altaRegistro("Test Event3", "Test Edition3", "Test Tipo", "testAsis3", fechaRegistro);
        
        DataRegistro registro = controlador.listarUnRegistroDeUsuario("Test Edition3", "testAsis3");
        assertNotNull(registro);
        assertEquals("testAsis3", registro.getAsistente());
        assertEquals("Test Edition3", registro.getEdicion());
    }
    
    @Test
    void testAceptarEdicion() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event4", "Test Description", "TE4", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Test Event4", "Test Edition4", "TE4", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        controlador.aceptarEdicion("Test Edition4", true);
        
        DataEdicion edicion = controlador.getInfoEdicion("Test Edition4");
        assertEquals("aceptada", edicion.getEstado());
    }
    
    @Test
    void testRechazarEdicion() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event5", "Test Description", "TE5", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Test Event5", "Test Edition5", "TE5", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        controlador.aceptarEdicion("Test Edition5", false);
        
        DataEdicion edicion = controlador.getInfoEdicion("Test Edition5");
        assertEquals("rechazada", edicion.getEstado());
    }
    
    @Test
    void testListarEdicionesOrganizador() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event6", "Test Description", "TE6", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Test Event6", "Test Edition6", "TE6", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        DataEdicion[] ediciones = controlador.listarEdicionesOrganizador("testOrg");
        assertNotNull(ediciones);
        assertEquals(true, ediciones.length >= 1);
    }
    
    @Test
    void testListarEdicionesAceptadas() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event7", "Test Description", "TE7", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Test Event7", "Test Edition7", "TE7", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        controlador.aceptarEdicion("Test Edition7", true);
        
        DataEdicion[] ediciones = controlador.listarEdicionesAceptadas();
        assertNotNull(ediciones);
        assertEquals(true, ediciones.length >= 1);
    }
    
    @Test
    void testListarEdicionesAceptadasEvento() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event8", "Test Description", "TE8", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Test Event8", "Test Edition8", "TE8", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        controlador.aceptarEdicion("Test Edition8", true);
        
        DataEdicion[] ediciones = controlador.listarEdicionesAceptadasEvento("Test Event8");
        assertNotNull(ediciones);
        assertEquals(1, ediciones.length);
    }
    
    @Test
    void testListarEdicionesIngresadas() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event9", "Test Description", "TE9", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Test Event9", "Test Edition9", "TE9", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        DataEdicion[] ediciones = controlador.listarEdicionesIngresadas();
        assertNotNull(ediciones);
        assertEquals(true, ediciones.length >= 1);
    }
    
    @Test
    void testListarEdicionesIngresadasEvento() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event10", "Test Description", "TE10", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Test Event10", "Test Edition10", "TE10", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        DataEdicion[] ediciones = controlador.listarEdicionesIngresadasEvento("Test Event10");
        assertNotNull(ediciones);
        assertEquals(1, ediciones.length);
    }
    
    @Test
    void testListarEdicionesRechazadas() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event11", "Test Description", "TE11", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Test Event11", "Test Edition11", "TE11", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        controlador.aceptarEdicion("Test Edition11", false);
        
        DataEdicion[] ediciones = controlador.listarEdicionesRechazadas();
        assertNotNull(ediciones);
        assertEquals(true, ediciones.length >= 1);
    }
    
    @Test
    void testListarEdicionesRechazadasEvento() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event12", "Test Description", "TE12", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Test Event12", "Test Edition12", "TE12", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        controlador.aceptarEdicion("Test Edition12", false);
        
        DataEdicion[] ediciones = controlador.listarEdicionesRechazadasEvento("Test Event12");
        assertNotNull(ediciones);
        assertEquals(1, ediciones.length);
    }
    
    @Test
    void testListarCategorias() throws Exception {
        try {  
            controlador.altaCategoria("Test Category 1");
            controlador.altaCategoria("Test Category 2");
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
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event13", "Test Description", "TE13", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Test Event13", "Test Edition13", "TE13", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        String nombreEvento = controlador.getEventoDeUnaEdicion("Test Edition13");
        assertEquals("Test Event13", nombreEvento);
    }
    
    @Test
    void testSetCostoRegistro() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        
        try {
            controladorUsuario.altaUsuario(
                "testAsis4", "Asistente Nombre", "asis4@test.com", "PerfilSinFoto.png", "contraseniaAsis",
                "Asistente", null, null, "Apellido", LocalDate.of(1990, 1, 1)
            );
        } catch (UsuarioRepetidoException e) {
            // No hacer nada
        }
        
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event14", "Test Description", "TE14", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        LocalDate fechaAltaEdi = LocalDate.now();
        LocalDate fechaFinEdi = LocalDate.now().plusDays(10);
        controlador.altaEdicionEvento("Test Event14", "Test Edition14", "TE14", "Test City", "Test Country", 
                                    fechaAltaEdi, fechaFinEdi, fechaAltaEdi, "testOrg");
        
        try {
            controlador.altaTipoRegistro("Test Event14", "Test Edition14", "Test Tipo", "Test Description", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        LocalDate fechaRegistro = fechaAltaEdi.plusDays(1);
        controlador.altaRegistro("Test Event14", "Test Edition14", "Test Tipo", "testAsis4", fechaRegistro);
        
        // Cambiar el costo del registro
        controlador.setCostoRegistro("testAsis4", "Test Edition14", "Test Tipo", 200);
        
        DataRegistro registro = controlador.listarUnRegistroDeUsuario("Test Edition14", "testAsis4");
        assertEquals(200, registro.getCosto());
    }
    
    @Test
    void testListarEdicionesOrganizadorAceptadas() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event15", "Test Description", "TE15", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Test Event15", "Test Edition15", "TE15", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        controlador.aceptarEdicion("Test Edition15", true);
        
        DataEdicion[] ediciones = controlador.listarEdicionesOrganizadorAceptadas("testOrg");
        assertNotNull(ediciones);
        assertEquals(true, ediciones.length >= 1);
    }
    
    @Test
    void testAltaEdicionEventoConImagen() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event16", "Test Description", "TE16", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Test Event16", "Test Edition16", "TE16", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg", "imagen.png");
        
        DataEdicion edicion = controlador.getInfoEdicion("Test Edition16");
        assertNotNull(edicion);
        assertEquals("Test Edition16", edicion.getNombre());
    }
    
    @Test
    void testNombreEdicionRepetido() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event17", "Test Description", "TE17", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Test Event17", "Test Edition17", "TE17", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        assertEquals(true, controlador.nombreEdicionRepetido("Test Edition17"));
        assertEquals(false, controlador.nombreEdicionRepetido("Edicion Inexistente"));
    }
    
    @Test
    void testAltaTipoRegistroRepetido() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event18", "Test Description", "TE18", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        controlador.altaEdicionEvento("Test Event18", "Test Edition18", "TE18", "Test City", "Test Country", 
                                    LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), "testOrg");
        
        try {
            controlador.altaTipoRegistro("Test Event18", "Test Edition18", "Test Tipo", "Test Description", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        assertThrows(TipoRegistroRepetidoException.class, () -> {
            controlador.altaTipoRegistro("Test Event18", "Test Edition18", "Test Tipo", "Test Description 2", 200, 100);
        });
    }
    
    @Test
    void testAltaRegistroEventoInexistente() throws Exception {
        try {
            controladorUsuario.altaUsuario(
                "testAsis5", "Asistente Nombre", "asis5@test.com", "PerfilSinFoto.png", "contraseniaAsis",
                "Asistente", null, null, "Apellido", LocalDate.of(1990, 1, 1)
            );
        } catch (UsuarioRepetidoException e) {
            // No hacer nada
        }
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaRegistro("Evento Inexistente", "Edicion Inexistente", "Tipo", "testAsis5", LocalDate.now());
        });
    }
    
    @Test
    void testAltaRegistroEdicionInexistente() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        
        try {
            controladorUsuario.altaUsuario(
                "testAsis6", "Asistente Nombre", "asis6@test.com", "PerfilSinFoto.png", "contraseniaAsis",
                "Asistente", null, null, "Apellido", LocalDate.of(1990, 1, 1)
            );
        } catch (UsuarioRepetidoException e) {
            // No hacer nada
        }
        
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event19", "Test Description", "TE19", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaRegistro("Test Event19", "Edicion Inexistente", "Tipo", "testAsis6", LocalDate.now());
        });
    }
    
    @Test
    void testAltaRegistroAsistenteInexistente() throws Exception {
        try {  
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event20", "Test Description", "TE20", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        LocalDate fechaAltaEdi = LocalDate.now();
        LocalDate fechaFinEdi = LocalDate.now().plusDays(10);
        controlador.altaEdicionEvento("Test Event20", "Test Edition20", "TE20", "Test City", "Test Country", 
                                    fechaAltaEdi, fechaFinEdi, fechaAltaEdi, "testOrg");
        
        try {
            controlador.altaTipoRegistro("Test Event20", "Test Edition20", "Test Tipo", "Test Description", 100, 50);
        } catch (TipoRegistroRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaRegistro("Test Event20", "Test Edition20", "Test Tipo", "AsistenteInexistente", fechaAltaEdi.plusDays(1));
        });
    }
    
    @Test
    void testListarRegistrosDeEdicionVacia() {
        DataRegistro[] registros = controlador.listarRegistrosDeEdicion("Edicion Inexistente");
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
            controlador.getInfoEdicion("Edicion Inexistente");
        });
    }
    
    @Test
    void testAceptarEdicionInexistente() {
        assertThrows(EdicionNoExisteException.class, () -> {
            controlador.aceptarEdicion("Edicion Inexistente", true);
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
            controlador.altaCategoria("Test Category");
        } catch (CategoriaRepetidaException e) {
            // No hace nada
        }
        List<String> categorias = new ArrayList<>();
        categorias.add("Test Category");
        
        try {
            controlador.altaEvento("Test Event Repetido", "Test Description", "TER", categorias, LocalDate.now(), "EventoSinFoto.png");
        } catch (EventoRepetidoException e) {
            fail("No debería lanzar excepción");
        }
        
        assertThrows(EventoRepetidoException.class, () -> {
            controlador.altaEvento("Test Event Repetido", "Test Description 2", "TER2", categorias, LocalDate.now(), "EventoSinFoto.png");
        });
    }
} 