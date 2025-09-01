package src.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logica.controladores.ControladorUsuario;
import logica.datatypes.DataUsuario;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import java.time.LocalDate;

public class ControladorUsuarioTest {
    
    private ControladorUsuario controlador;
    
    @BeforeEach
    void setUp() {
        controlador = ControladorUsuario.getInstance();
    }
    
    @Test
    void testGetInstance() {
        ControladorUsuario instancia1 = ControladorUsuario.getInstance();
        ControladorUsuario instancia2 = ControladorUsuario.getInstance();
        assertSame(instancia1, instancia2);
    }
    
    @Test
    void testAltaUsuarioOrganizador() throws UsuarioRepetidoException, UsuarioNoExisteException {
        controlador.altaUsuario("testOrg", "Test Organizador", "test@example.com", 
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        DataUsuario usuario = controlador.verInfoUsuario("testOrg");
        assertNotNull(usuario);
        assertEquals("testOrg", usuario.getNickname());
        assertEquals("Test Organizador", usuario.getNombre());
        assertEquals("test@example.com", usuario.getCorreo());
        assertEquals("Organizador", usuario.getTipo());
    }
    
    @Test
    void testAltaUsuarioAsistente() throws UsuarioRepetidoException, UsuarioNoExisteException {
        LocalDate fechaNac = LocalDate.of(1990, 1, 1);
        controlador.altaUsuario("testAsis", "Test Asistente", "test@example.com", 
                               "Asistente", null, null, "Test Apellido", fechaNac);
        
        DataUsuario usuario = controlador.verInfoUsuario("testAsis");
        assertNotNull(usuario);
        assertEquals("testAsis", usuario.getNickname());
        assertEquals("Test Asistente", usuario.getNombre());
        assertEquals("test@example.com", usuario.getCorreo());
        assertEquals("Asistente", usuario.getTipo());
    }
    
    @Test
    void testAltaUsuarioNicknameVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaUsuario("", "Test User", "test@example.com", 
                                   "Organizador", "Test Description", "http://test.com", null, null);
        });
    }
    
    @Test
    void testAltaUsuarioNombreVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaUsuario("testUser", "", "test@example.com", 
                                   "Organizador", "Test Description", "http://test.com", null, null);
        });
    }
    
    @Test
    void testAltaUsuarioCorreoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaUsuario("testUser", "Test User", "", 
                                   "Organizador", "Test Description", "http://test.com", null, null);
        });
    }
    
    @Test
    void testAltaUsuarioTipoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaUsuario("testUser", "Test User", "test@example.com", 
                                   "TipoInvalido", "Test Description", "http://test.com", null, null);
        });
    }
    
    @Test
    void testAltaUsuarioRepetido() throws UsuarioRepetidoException {
        controlador.altaUsuario("testUser", "Test User", "test@example.com", 
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        assertThrows(UsuarioRepetidoException.class, () -> {
            controlador.altaUsuario("testUser", "Test User 2", "test2@example.com", 
                                   "Asistente", null, null, "Test Apellido", LocalDate.of(1990, 1, 1));
        });
    }
    
    @Test
    void testAltaUsuarioCorreoRepetido() throws UsuarioRepetidoException {
        controlador.altaUsuario("testUser1", "Test User 1", "test@example.com", 
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        assertThrows(UsuarioRepetidoException.class, () -> {
            controlador.altaUsuario("testUser2", "Test User 2", "test@example.com", 
                                   "Asistente", null, null, "Test Apellido", LocalDate.of(1990, 1, 1));
        });
    }
    
    @Test
    void testVerInfoUsuario() throws UsuarioRepetidoException, UsuarioNoExisteException {
        controlador.altaUsuario("testUser", "Test User", "test@example.com", 
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        DataUsuario usuario = controlador.verInfoUsuario("testUser");
        assertNotNull(usuario);
        assertEquals("testUser", usuario.getNickname());
    }
    
    @Test
    void testVerInfoUsuarioInexistente() {
        assertThrows(UsuarioNoExisteException.class, () -> {
            controlador.verInfoUsuario("inexistente");
        });
    }
    
    @Test
    void testModificarUsuario() throws UsuarioRepetidoException, UsuarioNoExisteException {
        controlador.altaUsuario("testUser", "Test User", "test@example.com", 
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        controlador.modificarUsuario("testUser", "New Name", "New Description", "http://new.com", null, null);
        
        DataUsuario usuario = controlador.verInfoUsuario("testUser");
        assertEquals("New Name", usuario.getNombre());
    }
    
    @Test
    void testModificarUsuarioInexistente() {
        assertThrows(UsuarioNoExisteException.class, () -> {
            controlador.modificarUsuario("inexistente", "New Name", null, null, null, null);
        });
    }
    
    @Test
    void testGetOrganizador() throws UsuarioRepetidoException, UsuarioNoExisteException {
        controlador.altaUsuario("testOrg", "Test Organizador", "test@example.com", 
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        var organizador = controlador.getOrganizador("testOrg");
        assertNotNull(organizador);
        assertEquals("testOrg", organizador.getNickname());
    }
    
    @Test
    void testGetUsuarios() throws UsuarioRepetidoException, UsuarioNoExisteException {
        controlador.altaUsuario("testOrg", "Test Organizador", "test@example.com", 
                               "Organizador", "Test Description", "http://test.com", null, null);
        controlador.altaUsuario("testAsis", "Test Asistente", "test2@example.com", 
                               "Asistente", null, null, "Test Apellido", LocalDate.of(1990, 1, 1));
        
        DataUsuario[] usuarios = controlador.getUsuarios();
        assertNotNull(usuarios);
        assertEquals(2, usuarios.length);
    }
    
    @Test
    void testGetUsuariosVacio() {
        assertThrows(UsuarioNoExisteException.class, () -> {
            controlador.getUsuarios();
        });
    }
    
    @Test
    void testGetOrganizadores() throws UsuarioRepetidoException {
        controlador.altaUsuario("testOrg", "Test Organizador", "test@example.com", 
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        DataUsuario[] organizadores = controlador.getOrganizadores();
        assertNotNull(organizadores);
        assertEquals(1, organizadores.length);
        assertEquals("Organizador", organizadores[0].getTipo());
    }
    
    @Test
    void testGetAsistentes() throws UsuarioRepetidoException {
        controlador.altaUsuario("testAsis", "Test Asistente", "test@example.com", 
                               "Asistente", null, null, "Test Apellido", LocalDate.of(1990, 1, 1));
        
        DataUsuario[] asistentes = controlador.getAsistentes();
        assertNotNull(asistentes);
        assertEquals(1, asistentes.length);
        assertEquals("Asistente", asistentes[0].getTipo());
    }
} 