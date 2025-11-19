package test.logica;

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
        controlador.altaUsuario("testOrg", "Roberto García", "test@example.com", "PerfilSinFoto.png", "password",
                               "Organizador", "Organizador de eventos deportivos", "http://ejemplo.com", null, LocalDate.now());
        
        DataUsuario usuario = controlador.verInfoUsuario("testOrg");
        assertNotNull(usuario);
        assertEquals("testOrg", usuario.getNickname());
        assertEquals("Roberto García", usuario.getNombre());
        assertEquals("test@example.com", usuario.getCorreo());
        assertEquals("Organizador", usuario.getTipo());
    }
    
    @Test
    void testAltaUsuarioAsistente() throws UsuarioRepetidoException, UsuarioNoExisteException {
        LocalDate fechaNac = LocalDate.of(1990, 1, 1);
        controlador.altaUsuario("testAsis", "María López", "test@example.com", "PerfilSinFoto.png", "password",
                               "Asistente", null, null, "Fernández", fechaNac);
        
        DataUsuario usuario = controlador.verInfoUsuario("testAsis");
        assertNotNull(usuario);
        assertEquals("testAsis", usuario.getNickname());
        assertEquals("María López", usuario.getNombre());
        assertEquals("test@example.com", usuario.getCorreo());
        assertEquals("Asistente", usuario.getTipo());
    }
    
    @Test
    void testAltaUsuarioNicknameVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaUsuario("", "Pedro Gómez", "test@example.com", "PerfilSinFoto.png", "password",
                                   "Organizador", "Descripción del organizador", "http://ejemplo.com", null, null);
        });
    }
    
    @Test
    void testAltaUsuarioNicknameSinTexto() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaUsuario(null, "Pedro Gómez", "test@example.com", "PerfilSinFoto.png", "password",
                                   "Organizador", "Descripción del organizador", "http://ejemplo.com", null, null);
        });
    }
    
    @Test
    void testAltaUsuarioNombreVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaUsuario("testUser", "", "test@example.com", "PerfilSinFoto.png", "password",
                                   "Organizador", "Descripción del organizador", "http://ejemplo.com", null, null);
        });
    }
    
    @Test
    void testAltaUsuarioCorreoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaUsuario("testUser", "Pedro Gómez", "", "PerfilSinFoto.png", "password",
                                   "Organizador", "Descripción del organizador", "http://ejemplo.com", null, null);
        });
    }
    
    @Test
    void testAltaUsuarioTipoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.altaUsuario("testUser", "Pedro Gómez", "test@example.com", "PerfilSinFoto.png", "password",
                                   "TipoInvalido", "Descripción del usuario", "http://ejemplo.com", null, null);
        });
    }
    
    @Test
    void testAltaUsuarioRepetido() throws UsuarioRepetidoException {
        controlador.altaUsuario("testUser", "Carlos Martínez", "test@example.com", "PerfilSinFoto.png", "password",
                               "Organizador", "Organizador de conferencias", "http://ejemplo.com", null, null);
        
        assertThrows(UsuarioRepetidoException.class, () -> {
            controlador.altaUsuario("testUser", "Ana Rodríguez", "test2@example.com", "PerfilSinFoto.png", "password",
                                   "Asistente", null, null, "Pérez", LocalDate.of(1990, 1, 1));
        });
    }
    
    @Test
    void testAltaUsuarioCorreoRepetido() throws UsuarioRepetidoException {
        controlador.altaUsuario("testUser1", "Juan Sánchez", "test@example.com", "PerfilSinFoto.png", "password",
                               "Organizador", "Organizador de eventos", "http://ejemplo.com", null, null);
        
        assertThrows(UsuarioRepetidoException.class, () -> {
            controlador.altaUsuario("testUser2", "Laura Díaz", "test@example.com", "PerfilSinFoto.png", "password",
                                   "Asistente", null, null, "González", LocalDate.of(1990, 1, 1));
        });
    }
    
    @Test
    void testVerInfoUsuario() throws UsuarioRepetidoException, UsuarioNoExisteException {
        controlador.altaUsuario("testUser", "Diego Ramírez", "test@example.com", "PerfilSinFoto.png", "password",
                               "Organizador", "Organizador profesional", "http://ejemplo.com", null, null);
        
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
        controlador.altaUsuario("testUser", "Miguel Torres", "test@example.com", "PerfilSinFoto.png", "password",
                               "Organizador", "Organizador de eventos", "http://ejemplo.com", null, null);
        
        controlador.modificarUsuario("testUser", "Miguel Ángel Torres", "Organizador de eventos internacionales", "PerfilSinFoto.png", "http://nuevo.com", null, null);
        
        DataUsuario usuario = controlador.verInfoUsuario("testUser");
        assertEquals("Miguel Ángel Torres", usuario.getNombre());
    }
    
    @Test
    void testModificarUsuarioInexistente() {
        assertThrows(UsuarioNoExisteException.class, () -> {
            controlador.modificarUsuario("inexistente", "Nuevo Nombre", null, null, null, null, null);
        });
    }
    
    @Test
    void testGetOrganizador() throws UsuarioRepetidoException, UsuarioNoExisteException {
        controlador.altaUsuario("testOrg", "Fernando Silva", "test@example.com", "PerfilSinFoto.png", "password",
                               "Organizador", "Organizador de eventos culturales", "http://ejemplo.com", null, null);
        
        DataUsuario organizador = controlador.getOrganizador("testOrg");
        assertNotNull(organizador);
        assertEquals("testOrg", organizador.getNickname());
    }
    
    @Test
    void testGetUsuarios() throws UsuarioRepetidoException, UsuarioNoExisteException {
        controlador.altaUsuario("testOrg", "Patricia Morales", "test@example.com", "PerfilSinFoto.png", "password",
                               "Organizador", "Organizadora de congresos", "http://ejemplo.com", null, null);
        controlador.altaUsuario("testAsis", "Sofía Vega", "test2@example.com", "PerfilSinFoto.png", "password",
                               "Asistente", null, null, "Hernández", LocalDate.of(1990, 1, 1));
        
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
        controlador.altaUsuario("testOrg", "Ricardo Mendoza", "test@example.com", "PerfilSinFoto.png", "password",
                               "Organizador", "Organizador de eventos empresariales", "http://ejemplo.com", null, null);
        
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
        controlador.altaUsuario("testAsis", "Valentina Castro", "test@example.com", "PerfilSinFoto.png", "password",
                               "Asistente", null, null, "Ruiz", LocalDate.of(1990, 1, 1));
        
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
        controlador.altaUsuario("testAsistente", "Andrés Ortiz", "asistente@example.com", "PerfilSinFoto.png", "password",
                               "Asistente", null, null, "Jiménez", LocalDate.of(1990, 1, 1));
        
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
        controlador.altaUsuario("testLogin", "Martín Flores", "login@example.com", "PerfilSinFoto.png", "password123",
                               "Organizador", "Organizador de eventos académicos", "http://ejemplo.com", null, null);
        
        DataUsuario usuario = controlador.login("testLogin", "password123");
        assertNotNull(usuario);
        assertEquals("testLogin", usuario.getNickname());
    }
    
    @Test
    void testLoginConCorreo() throws UsuarioRepetidoException, UsuarioNoExisteException, PasswordIncorrectaException {
        controlador.altaUsuario("testLogin2", "Gabriela Romero", "login2@example.com", "PerfilSinFoto.png", "password123",
                               "Organizador", "Organizadora de ferias", "http://ejemplo.com", null, null);
        
        DataUsuario usuario = controlador.login("login2@example.com", "password123");
        assertNotNull(usuario);
        assertEquals("testLogin2", usuario.getNickname());
    }
    
    @Test
    void testLoginPasswordIncorrecta() throws UsuarioRepetidoException {
        controlador.altaUsuario("testLogin3", "Daniel Vargas", "login3@example.com", "PerfilSinFoto.png", "password123",
                               "Organizador", "Organizador de seminarios", "http://ejemplo.com", null, null);
        
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
        controlador.altaUsuario("testLogin4", "Carolina Navarro", "login4@example.com", "PerfilSinFoto.png", "password123",
                               "Organizador", "Organizadora de talleres", "http://ejemplo.com", null, null);
        
        assertThrows(PasswordIncorrectaException.class, () -> {
            controlador.login("testLogin4", null);
        });
    }
    
    @Test
    void testModificarUsuarioAsistente() throws UsuarioRepetidoException, UsuarioNoExisteException {
        controlador.altaUsuario("testModAsis", "Lucía Medina", "modasis@example.com", "PerfilSinFoto.png", "password",
                               "Asistente", null, null, "Cordero", LocalDate.of(1990, 1, 1));
        
        controlador.modificarUsuario("testModAsis", "Lucía María Medina", null, "new.png", null, "Cordero Suárez", LocalDate.of(1991, 2, 2));
        
        DataUsuario usuario = controlador.verInfoUsuario("testModAsis");
        assertEquals("Lucía María Medina", usuario.getNombre());
        assertEquals("Cordero Suárez", usuario.getApellido());
        assertEquals(LocalDate.of(1991, 2, 2), usuario.getFechaNacimiento());
    }
    
    @Test
    void testCambiarContrasenia() {
      try {
        controlador.altaUsuario("nickname", "Javier Ramos", "user@example.com", "PerfilSinFoto.png", "password",
          "Asistente", null, null, "Molina", LocalDate.of(1990, 1, 1));
        controlador.cambiarContrasenia("nickname", "nuevaPass123");
        Usuario usuario = ManejadorUsuario.getInstance().obtenerPorNickname("nickname");
        assertEquals(usuario.getPassword(), "nuevaPass123");
        } catch (UsuarioRepetidoException excepcion) {
          fail();
        }
      
    }
} 