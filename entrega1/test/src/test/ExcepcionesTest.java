package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import excepciones.CategoriaRepetidaException;
import excepciones.EdicionNoExisteException;
import excepciones.EventoNoExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import excepciones.EventoRepetidoException;
import excepciones.PasswordIncorrectaException;
import excepciones.TipoRegistroRepetidoException;
import excepciones.TransicionEstadoInvalidaException;

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
    void testPasswordIncorrectaException() {
        String mensaje = "La contraseña es incorrecta";
        PasswordIncorrectaException exception = new PasswordIncorrectaException(mensaje);
        
        assertNotNull(exception);
        assertEquals(mensaje, exception.getMessage());
    }
    
    @Test
    void testPasswordIncorrectaExceptionMensajeVacio() {
        PasswordIncorrectaException exception = new PasswordIncorrectaException("");
        assertEquals("", exception.getMessage());
    }
    
    @Test
    void testPasswordIncorrectaExceptionMensajeNull() {
        PasswordIncorrectaException exception = new PasswordIncorrectaException(null);
        assertNull(exception.getMessage());
    }
    
    @Test
    void testTipoRegistroRepetidoException() {
        String mensaje = "El tipo de registro ya existe";
        TipoRegistroRepetidoException exception = new TipoRegistroRepetidoException(mensaje);
        
        assertNotNull(exception);
        assertEquals(mensaje, exception.getMessage());
    }
    
    @Test
    void testTipoRegistroRepetidoExceptionMensajeVacio() {
        TipoRegistroRepetidoException exception = new TipoRegistroRepetidoException("");
        assertEquals("", exception.getMessage());
    }
    
    @Test
    void testTipoRegistroRepetidoExceptionMensajeNull() {
        TipoRegistroRepetidoException exception = new TipoRegistroRepetidoException(null);
        assertNull(exception.getMessage());
    }
    
    @Test
    void testTransicionEstadoInvalidaException() {
        String mensaje = "Transición de estado inválida";
        TransicionEstadoInvalidaException exception = new TransicionEstadoInvalidaException(mensaje);
        
        assertNotNull(exception);
        assertEquals(mensaje, exception.getMessage());
    }
    
    @Test
    void testTransicionEstadoInvalidaExceptionMensajeVacio() {
        TransicionEstadoInvalidaException exception = new TransicionEstadoInvalidaException("");
        assertEquals("", exception.getMessage());
    }
    
    @Test
    void testTransicionEstadoInvalidaExceptionMensajeNull() {
        TransicionEstadoInvalidaException exception = new TransicionEstadoInvalidaException(null);
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
        assertTrue(new PasswordIncorrectaException("test") instanceof Exception);
        assertTrue(new TipoRegistroRepetidoException("test") instanceof Exception);
        assertTrue(new TransicionEstadoInvalidaException("test") instanceof Exception);
    }
} 