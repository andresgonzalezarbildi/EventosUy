package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.UsuarioNoExisteException;
import logica.clases.Asistente;
import logica.clases.Organizador;
import logica.clases.Usuario;
import logica.manejadores.ManejadorUsuario;

public class UsuarioTest {
    
    private Usuario usuario;
    private ManejadorUsuario manejador;
    
    @BeforeEach
    void setUp() {
        usuario = new Usuario("testUser", "Test User", "test@example.com", "PerfilSinFoto.png", "password");
        manejador = ManejadorUsuario.getInstance();
        // Limpiar el estado del singleton para cada test
        limpiarManejador();
    }
    
    @Test
    void testConstructor() {
        assertNotNull(usuario);
        assertEquals("testUser", usuario.getNickname());
        assertEquals("Test User", usuario.getNombre());
        assertEquals("test@example.com", usuario.getCorreo());
    }
    
    @Test
    void testGetNickname() {
        assertEquals("testUser", usuario.getNickname());
    }
    
    @Test
    void testGetNombre() {
        assertEquals("Test User", usuario.getNombre());
    }
    
    @Test
    void testGetCorreo() {
        assertEquals("test@example.com", usuario.getCorreo());
    }
    
    @Test
    void testSetNickname() {
        usuario.setNickname("newNickname");
        assertEquals("newNickname", usuario.getNickname());
    }
    
    @Test
    void testSetNombre() {
        usuario.setNombre("New Name");
        assertEquals("New Name", usuario.getNombre());
    }
    
    @Test
    void testSetCorreo() {
        usuario.setCorreo("new@example.com");
        assertEquals("new@example.com", usuario.getCorreo());
    }
    
    @Test
    void testSetNicknameNull() {
        usuario.setNickname(null);
        assertNull(usuario.getNickname());
    }
    
    @Test
    void testSetNombreNull() {
        usuario.setNombre(null);
        assertNull(usuario.getNombre());
    }
    
    @Test
    void testSetCorreoNull() {
        usuario.setCorreo(null);
        assertNull(usuario.getCorreo());
    }
    
    @AfterEach
    void tearDown() {
        limpiarManejador();
    }
    
    private void limpiarManejador() {
    	 try {
    	        // 1) resetear la instancia a null
    	        Field fInst = ManejadorUsuario.class.getDeclaredField("instancia");
    	        fInst.setAccessible(true);
    	        fInst.set(null, null);

    	        // 2) forzar nueva instancia vacía
    	        ManejadorUsuario fresh = ManejadorUsuario.getInstance();

    	        // 3) (opcional) limpiar mapas por las dudas
    	        Field fOrg = ManejadorUsuario.class.getDeclaredField("organizadores");
    	        Field fAsi = ManejadorUsuario.class.getDeclaredField("asistentes");
    	        fOrg.setAccessible(true);
    	        fAsi.setAccessible(true);
    	        ((Map<?, ?>) fOrg.get(fresh)).clear();
    	        ((Map<?, ?>) fAsi.get(fresh)).clear();

    	        // 4) reasignar al campo de la clase de test
    	        this.manejador = fresh;

    	    } catch (ReflectiveOperationException e) {
    	        throw new RuntimeException("No se pudo limpiar el estado de ManejadorUsuario", e);
    	    }
    }
    
    @Test
    void testGetInstance() {
        ManejadorUsuario instancia1 = ManejadorUsuario.getInstance();
        ManejadorUsuario instancia2 = ManejadorUsuario.getInstance();
        assertSame(instancia1, instancia2);
    }
    
    @Test
    void testExisteNickname() {
        assertFalse(manejador.existeNickname("testUser"));
        
        Organizador org = new Organizador("testUser", "Test User", "test@example.com", "PerfilSinFoto.png", "password", "Test Description", "http://test.com");
        manejador.agregarOrganizador(org);
        
        assertTrue(manejador.existeNickname("testUser"));
    }
    
    @Test
    void testExisteCorreo() {
        assertFalse(manejador.existeCorreo("test@example.com"));
        
        Organizador org = new Organizador("testUser", "Test User", "test@example.com", "PerfilSinFoto.png", "password", "Test Description", "http://test.com");
        manejador.agregarOrganizador(org);
        
        assertTrue(manejador.existeCorreo("test@example.com"));
        assertTrue(manejador.existeCorreo("TEST@EXAMPLE.COM")); // Case insensitive
    }
    
    @Test
    void testAgregarOrganizador() {
        Organizador org = new Organizador("testUser", "Test User", "test@example.com", "PerfilSinFoto.png", "password", "Test Description", "http://test.com");
        manejador.agregarOrganizador(org);
        
        assertTrue(manejador.existeNickname("testUser"));
        assertTrue(manejador.existeCorreo("test@example.com"));
    }
    
    @Test
    void testAgregarAsistente() {
        Asistente asis = new Asistente("testUser", "Test User", "test@example.com", "PerfilSinFoto.png", "password", "Test Apellido", LocalDate.of(1990, 1, 1));
        manejador.agregarAsistente(asis);
        
        assertTrue(manejador.existeNickname("testUser"));
        assertTrue(manejador.existeCorreo("test@example.com"));
    }
    
    @Test
    void testObtenerPorNickname() {
        Organizador org = new Organizador("testUser", "Test User", "test@example.com", "PerfilSinFoto.png", "password", "Test Description", "http://test.com");
        manejador.agregarOrganizador(org);
        
        assertNotNull(manejador.obtenerPorNickname("testUser"));
        assertNull(manejador.obtenerPorNickname("inexistente"));
    }
    
    @Test
    void testGetOrganizador() throws UsuarioNoExisteException {
        Organizador org = new Organizador("testUser", "Test User", "test@example.com", "PerfilSinFoto.png", "password", "Test Description", "http://test.com");
        manejador.agregarOrganizador(org);
        
        Organizador orgObtenido = manejador.getOrganizador("testUser");
        assertNotNull(orgObtenido);
        assertEquals("testUser", orgObtenido.getNickname());
    }
    
    @Test
    void testGetOrganizadorInexistente() {
        assertThrows(UsuarioNoExisteException.class, () -> {
            manejador.getOrganizador("inexistente");
        });
    }
    
    @Test
    void testGetAsistente() throws UsuarioNoExisteException {
        Asistente asis = new Asistente("testUser", "Test User", "test@example.com", "PerfilSinFoto.png", "password", "Test Apellido", LocalDate.of(1990, 1, 1));
        manejador.agregarAsistente(asis);
        
        Asistente asisObtenido = manejador.getAsistente("testUser");
        assertNotNull(asisObtenido);
        assertEquals("testUser", asisObtenido.getNickname());
    }
    
    @Test
    void testGetAsistenteInexistente() {
        assertThrows(UsuarioNoExisteException.class, () -> {
            manejador.getAsistente("inexistente");
        });
    }
    
    @Test
    void testObtenerTodosOrganizadores() {
        assertTrue(manejador.obtenerTodosOrganizadores().isEmpty());
        
        Organizador org1 = new Organizador("user1", "User 1", "user1@example.com", "PerfilSinFoto.png", "password", "Desc 1", "http://1.com");
        Organizador org2 = new Organizador("user2", "User 2", "user2@example.com", "PerfilSinFoto.png", "password", "Desc 2", "http://2.com");
        
        manejador.agregarOrganizador(org1);
        manejador.agregarOrganizador(org2);
        
        assertEquals(2, manejador.obtenerTodosOrganizadores().size());
    }
    
    @Test
    void testObtenerTodosAsistentes() {
        assertTrue(manejador.obtenerTodosAsistentes().isEmpty());
        
        Asistente asis1 = new Asistente("user1", "User 1", "user1@example.com", "PerfilSinFoto.png", "password", "Apellido 1", LocalDate.of(1990, 1, 1));
        Asistente asis2 = new Asistente("user2", "User 2", "user2@example.com", "PerfilSinFoto.png", "password", "Apellido 2", LocalDate.of(1990, 1, 1));
        
        manejador.agregarAsistente(asis1);
        manejador.agregarAsistente(asis2);
        
        assertEquals(2, manejador.obtenerTodosAsistentes().size());
    }
} 