package src.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import excepciones.CategoriaRepetidaException;
import excepciones.EdicionNoExisteException;
import excepciones.EventoNoExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import excepciones.EventoRepetidoException;

public class ExcepcionesTest {
    
    @Test
    void testCategoriaRepetidaException() {
        String mensaje = "La categoría ya existe";
        CategoriaRepetidaException exception = new CategoriaRepetidaException(mensaje);
        
        assertNotNull(exception);
        assertEquals(mensaje, exception.getMessage());
    }
    
    @Test
    void testCategoriaRepetidaExceptionMensajeVacio() {
        CategoriaRepetidaException exception = new CategoriaRepetidaException("");
        assertEquals("", exception.getMessage());
    }
    
    @Test
    void testCategoriaRepetidaExceptionMensajeNull() {
        CategoriaRepetidaException exception = new CategoriaRepetidaException(null);
        assertNull(exception.getMessage());
    }
    
    @Test
    void testEdicionNoExisteException() {
        String mensaje = "La edición no existe";
        EdicionNoExisteException exception = new EdicionNoExisteException(mensaje);
        
        assertNotNull(exception);
        assertEquals(mensaje, exception.getMessage());
    }
    
    @Test
    void testEdicionNoExisteExceptionMensajeVacio() {
        EdicionNoExisteException exception = new EdicionNoExisteException("");
        assertEquals("", exception.getMessage());
    }
    
    @Test
    void testEdicionNoExisteExceptionMensajeNull() {
        EdicionNoExisteException exception = new EdicionNoExisteException(null);
        assertNull(exception.getMessage());
    }
    
    @Test
    void testEventoNoExisteException() {
        String mensaje = "El evento no existe";
        EventoNoExisteException exception = new EventoNoExisteException(mensaje);
        
        assertNotNull(exception);
        assertEquals(mensaje, exception.getMessage());
    }
    
    @Test
    void testEventoNoExisteExceptionMensajeVacio() {
        EventoNoExisteException exception = new EventoNoExisteException("");
        assertEquals("", exception.getMessage());
    }
    
    @Test
    void testEventoNoExisteExceptionMensajeNull() {
        EventoNoExisteException exception = new EventoNoExisteException(null);
        assertNull(exception.getMessage());
    }
    
    @Test
    void testUsuarioNoExisteException() {
        String mensaje = "El usuario no existe";
        UsuarioNoExisteException exception = new UsuarioNoExisteException(mensaje);
        
        assertNotNull(exception);
        assertEquals(mensaje, exception.getMessage());
    }
    
    @Test
    void testUsuarioNoExisteExceptionMensajeVacio() {
        UsuarioNoExisteException exception = new UsuarioNoExisteException("");
        assertEquals("", exception.getMessage());
    }
    
    @Test
    void testUsuarioNoExisteExceptionMensajeNull() {
        UsuarioNoExisteException exception = new UsuarioNoExisteException(null);
        assertNull(exception.getMessage());
    }
    
    @Test
    void testUsuarioRepetidoException() {
        String mensaje = "El usuario ya existe";
        UsuarioRepetidoException exception = new UsuarioRepetidoException(mensaje);
        
        assertNotNull(exception);
        assertEquals(mensaje, exception.getMessage());
    }
    
    @Test
    void testUsuarioRepetidoExceptionMensajeVacio() {
        UsuarioRepetidoException exception = new UsuarioRepetidoException("");
        assertEquals("", exception.getMessage());
    }
    
    @Test
    void testUsuarioRepetidoExceptionMensajeNull() {
        UsuarioRepetidoException exception = new UsuarioRepetidoException(null);
        assertNull(exception.getMessage());
    }
    
    @Test
    void testEventoRepetidoException() {
        String mensaje = "El evento ya existe";
        EventoRepetidoException exception = new EventoRepetidoException(mensaje);
        
        assertNotNull(exception);
        assertEquals(mensaje, exception.getMessage());
    }
    
    @Test
    void testEventoRepetidoExceptionMensajeVacio() {
        EventoRepetidoException exception = new EventoRepetidoException("");
        assertEquals("", exception.getMessage());
    }
    
    @Test
    void testEventoRepetidoExceptionMensajeNull() {
        EventoRepetidoException exception = new EventoRepetidoException(null);
        assertNull(exception.getMessage());
    }
    
    @Test
    void testExcepcionesHerencia() {
        // Verificar que todas las excepciones heredan de Exception
        assertTrue(new CategoriaRepetidaException("test") instanceof Exception);
        assertTrue(new EdicionNoExisteException("test") instanceof Exception);
        assertTrue(new EventoNoExisteException("test") instanceof Exception);
        assertTrue(new UsuarioNoExisteException("test") instanceof Exception);
        assertTrue(new UsuarioRepetidoException("test") instanceof Exception);
        assertTrue(new EventoRepetidoException("test") instanceof Exception);
    }
} 