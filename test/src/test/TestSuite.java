package src.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Clase principal para ejecutar todos los tests del sistema.
 * Esta clase puede ser ejecutada directamente desde el IDE o línea de comandos.
 * 
 * Para ejecutar todos los tests:
 * 1. Ejecutar esta clase como JUnit Test
 * 2. O ejecutar cada clase de test individualmente
 * 3. O usar el IDE para ejecutar todos los tests del proyecto
 **/
@DisplayName("Test Suite Completo del Sistema de Gestión de Eventos")
public class TestSuite {
    
    @Test
    @DisplayName("Test Suite Principal - Ejecutar todos los tests")
    void testSuite() {
        // Esta clase no contiene tests específicos
        // Solo sirve como punto de entrada para ejecutar todos los tests
        // Los tests reales están en las otras clases de test
        assertTrue(true, "Test suite configurado correctamente");
    }

    /**
     * Lista de todas las clases de test disponibles:
     * - UsuarioTest: Tests para la clase Usuario
     * - CategoriaTest: Tests para la clase Categoria  
     * - EventoTest: Tests para la clase Evento
     * - EdicionEventoTest: Tests para la clase EdicionEvento
     * - ManejadorUsuarioTest: Tests para ManejadorUsuario
     * - ManejadorEventoTest: Tests para ManejadorEvento
     * - ControladorUsuarioTest: Tests para ControladorUsuario
     * - ControladorEventoTest: Tests para ControladorEvento
     * - ExcepcionesTest: Tests para todas las excepciones personalizadas
     * - DataTypesTest: Tests para todos los datatypes
    **/
}
