package src.test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import logica.clases.Usuario;
import logica.controladores.ControladorUsuario;
import logica.manejadores.ManejadorUsuario;
import logica.datatypes.DataUsuario;
import excepciones.PasswordIncorrectaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ControladorUsuarioTest {
    
    private ControladorUsuario controlador;
    
    @BeforeEach
    void setUp() {
      controlador = ControladorUsuario.getInstance();
      controlador.limpar();
    }
    
    @Test
    void testgetInstance() {
      ControladorUsuario controlador2 = ControladorUsuario.getInstance();
      assertSame(controlador, controlador2);
    }
    
    @Test
    void testAltaUsuarioOrganizador() throws UsuarioRepetidoException, UsuarioNoExisteException {
        controlador.altaUsuario("testOrg", "Test Organizador", "test@example.com", "PerfilSinFoto.png", "password",
                               "Organizador", "Test Description", "http://test.com", null, LocalDate.now());
        
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
        controlador.altaUsuario("testAsis", "Test Asistente", "test@example.com", "PerfilSinFoto.png", "password",
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
            controlador.altaUsuario("", "Test User", "test@example.com", "PerfilSinFoto.png", "password",
                                   "Organizador", "Test Description", "http://test.com", null, null);
        });
    }
    
    @Test
    void testAltaUsuarioNicknameSinTexto() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaUsuario(null, "Test User", "test@example.com", "PerfilSinFoto.png", "password",
                                   "Organizador", "Test Description", "http://test.com", null, null);
        });
    }
    
    @Test
    void testAltaUsuarioNombreVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaUsuario("testUser", "", "test@example.com", "PerfilSinFoto.png", "password",
                                   "Organizador", "Test Description", "http://test.com", null, null);
        });
    }
    
    @Test
    void testAltaUsuarioCorreoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaUsuario("testUser", "Test User", "", "PerfilSinFoto.png", "password",
                                   "Organizador", "Test Description", "http://test.com", null, null);
        });
    }
    
    @Test
    void testAltaUsuarioTipoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaUsuario("testUser", "Test User", "test@example.com", "PerfilSinFoto.png", "password",
                                   "TipoInvalido", "Test Description", "http://test.com", null, null);
        });
    }
    
    @Test
    void testAltaUsuarioRepetido() throws UsuarioRepetidoException {
        controlador.altaUsuario("testUser", "Test User", "test@example.com", "PerfilSinFoto.png", "password",
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        assertThrows(UsuarioRepetidoException.class, () -> {
            controlador.altaUsuario("testUser", "Test User 2", "test2@example.com", "PerfilSinFoto.png", "password",
                                   "Asistente", null, null, "Test Apellido", LocalDate.of(1990, 1, 1));
        });
    }
    
    @Test
    void testAltaUsuarioCorreoRepetido() throws UsuarioRepetidoException {
        controlador.altaUsuario("testUser1", "Test User 1", "test@example.com", "PerfilSinFoto.png", "password",
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        assertThrows(UsuarioRepetidoException.class, () -> {
            controlador.altaUsuario("testUser2", "Test User 2", "test@example.com", "PerfilSinFoto.png", "password",
                                   "Asistente", null, null, "Test Apellido", LocalDate.of(1990, 1, 1));
        });
    }
    
    @Test
    void testVerInfoUsuario() throws UsuarioRepetidoException, UsuarioNoExisteException {
        controlador.altaUsuario("testUser", "Test User", "test@example.com", "PerfilSinFoto.png", "password",
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
        controlador.altaUsuario("testUser", "Test User", "test@example.com", "PerfilSinFoto.png", "password",
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        controlador.modificarUsuario("testUser", "New Name", "New Description", "PerfilSinFoto.png", "http://new.com", null, null);
        
        DataUsuario usuario = controlador.verInfoUsuario("testUser");
        assertEquals("New Name", usuario.getNombre());
    }
    
    @Test
    void testModificarUsuarioInexistente() {
        assertThrows(UsuarioNoExisteException.class, () -> {
            controlador.modificarUsuario("inexistente", "New Name", null, null, null, null, null);
        });
    }
    
    @Test
    void testGetOrganizador() throws UsuarioRepetidoException, UsuarioNoExisteException {
        controlador.altaUsuario("testOrg", "Test Organizador", "test@example.com", "PerfilSinFoto.png", "password",
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        DataUsuario organizador = controlador.getOrganizador("testOrg");
        assertNotNull(organizador);
        assertEquals("testOrg", organizador.getNickname());
    }
    
    @Test
    void testGetUsuarios() throws UsuarioRepetidoException, UsuarioNoExisteException {
        controlador.altaUsuario("testOrg", "Test Organizador", "test@example.com", "PerfilSinFoto.png", "password",
                               "Organizador", "Test Description", "http://test.com", null, null);
        controlador.altaUsuario("testAsis", "Test Asistente", "test2@example.com", "PerfilSinFoto.png", "password",
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
        controlador.altaUsuario("testOrg", "Test Organizador", "test@example.com", "PerfilSinFoto.png", "password",
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        try {
          DataUsuario[] organizadores = controlador.getOrganizadores();
          assertNotNull(organizadores);
          assertEquals(1, organizadores.length);
          assertEquals("Organizador", organizadores[0].getTipo());
        } catch (UsuarioNoExisteException e) {
          // No hacer nada
        }
      }
    
    @Test
    void testGetAsistentes() throws UsuarioRepetidoException {
        controlador.altaUsuario("testAsis", "Test Asistente", "test@example.com", "PerfilSinFoto.png", "password",
                               "Asistente", null, null, "Test Apellido", LocalDate.of(1990, 1, 1));
        
        try {
          DataUsuario[] asistentes = controlador.getAsistentes();
          assertNotNull(asistentes);
          assertEquals(1, asistentes.length);
          assertEquals("Asistente", asistentes[0].getTipo());
        } catch (UsuarioNoExisteException e) {
          // No hacer nada
        }
    }
    
    @Test
    void testGetAsistente() throws UsuarioRepetidoException, UsuarioNoExisteException {
        controlador.altaUsuario("testAsistente", "Test Asistente", "asistente@example.com", "PerfilSinFoto.png", "password",
                               "Asistente", null, null, "Test Apellido", LocalDate.of(1990, 1, 1));
        
        DataUsuario asistente = controlador.getAsistente("testAsistente");
        assertNotNull(asistente);
        assertEquals("testAsistente", asistente.getNickname());
        assertEquals("Asistente", asistente.getTipo());
    }
    
    @Test
    void testGetAsistenteInexistente() {
        assertThrows(UsuarioNoExisteException.class, () -> {
            controlador.getAsistente("inexistente");
        });
    }
    
    @Test
    void testLoginExitoso() throws UsuarioRepetidoException, UsuarioNoExisteException, PasswordIncorrectaException {
        controlador.altaUsuario("testLogin", "Test Usuario", "login@example.com", "PerfilSinFoto.png", "password123",
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        DataUsuario usuario = controlador.login("testLogin", "password123");
        assertNotNull(usuario);
        assertEquals("testLogin", usuario.getNickname());
    }
    
    @Test
    void testLoginConCorreo() throws UsuarioRepetidoException, UsuarioNoExisteException, PasswordIncorrectaException {
        controlador.altaUsuario("testLogin2", "Test Usuario", "login2@example.com", "PerfilSinFoto.png", "password123",
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        DataUsuario usuario = controlador.login("login2@example.com", "password123");
        assertNotNull(usuario);
        assertEquals("testLogin2", usuario.getNickname());
    }
    
    @Test
    void testLoginPasswordIncorrecta() throws UsuarioRepetidoException {
        controlador.altaUsuario("testLogin3", "Test Usuario", "login3@example.com", "PerfilSinFoto.png", "password123",
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        assertThrows(PasswordIncorrectaException.class, () -> {
            controlador.login("testLogin3", "wrongPassword");
        });
    }
    
    @Test
    void testLoginUsuarioInexistente() {
        assertThrows(UsuarioNoExisteException.class, () -> {
            controlador.login("usuarioInexistente", "password123");
        });
    }
    
    @Test
    void testLoginIdentificadorVacio() {
        assertThrows(UsuarioNoExisteException.class, () -> {
            controlador.login("", "password123");
        });
    }
    
    @Test
    void testLoginPasswordNull() throws UsuarioRepetidoException {
        controlador.altaUsuario("testLogin4", "Test Usuario", "login4@example.com", "PerfilSinFoto.png", "password123",
                               "Organizador", "Test Description", "http://test.com", null, null);
        
        assertThrows(PasswordIncorrectaException.class, () -> {
            controlador.login("testLogin4", null);
        });
    }
    
    @Test
    void testModificarUsuarioAsistente() throws UsuarioRepetidoException, UsuarioNoExisteException {
        controlador.altaUsuario("testModAsis", "Test Asistente", "modasis@example.com", "PerfilSinFoto.png", "password",
                               "Asistente", null, null, "Test Apellido", LocalDate.of(1990, 1, 1));
        
        controlador.modificarUsuario("testModAsis", "New Name", null, "new.png", null, "New Apellido", LocalDate.of(1991, 2, 2));
        
        DataUsuario usuario = controlador.verInfoUsuario("testModAsis");
        assertEquals("New Name", usuario.getNombre());
        assertEquals("New Apellido", usuario.getApellido());
        assertEquals(LocalDate.of(1991, 2, 2), usuario.getFechaNacimiento());
    }
    
    @Test
    void testCambiarContrasenia() {
      try {
        controlador.altaUsuario("nickname", "nombre", "user@example.com", "PerfilSinFoto.png", "password",
          "Asistente", null, null, "Test Apellido", LocalDate.of(1990, 1, 1));
        controlador.cambiarContrasenia("nickname", "juan");
        Usuario usuario = ManejadorUsuario.getInstance().obtenerPorNickname("nickname");
        assertEquals(usuario.getPassword(), "juan");
        } catch (UsuarioRepetidoException excepcion) {
          fail();
        }
      
    }
} 