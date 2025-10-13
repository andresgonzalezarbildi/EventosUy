package src.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import logica.Fabrica;
import logica.interfaces.ICargaDatos;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;

public class FabricaTest {
    
    @Test
    void testGetInstance() {
        Fabrica instancia1 = Fabrica.getInstance();
        Fabrica instancia2 = Fabrica.getInstance();
        assertSame(instancia1, instancia2);
        assertNotNull(instancia1);
    }
    
    @Test
    void testGetControladorUsuario() {
        Fabrica fabrica = Fabrica.getInstance();
        IControladorUsuario controlador1 = fabrica.getControladorUsuario();
        IControladorUsuario controlador2 = fabrica.getControladorUsuario();
        
        assertNotNull(controlador1);
        assertSame(controlador1, controlador2);
    }
    
    @Test
    void testGetControladorEvento() {
        Fabrica fabrica = Fabrica.getInstance();
        IControladorEvento controlador1 = fabrica.getControladorEvento();
        IControladorEvento controlador2 = fabrica.getControladorEvento();
        
        assertNotNull(controlador1);
        assertSame(controlador1, controlador2);
    }
    
    @Test
    void testGetCargaDatos() {
        Fabrica fabrica = Fabrica.getInstance();
        ICargaDatos cargaDatos1 = fabrica.getCargaDatos();
        ICargaDatos cargaDatos2 = fabrica.getCargaDatos();
        
        assertNotNull(cargaDatos1);
        assertSame(cargaDatos1, cargaDatos2);
    }
    
    @Test
    void testGetCargaDatosUtilizaControladores() {
        Fabrica fabrica = Fabrica.getInstance();
        
        // Obtener los controladores primero
        IControladorUsuario controladorUsuario = fabrica.getControladorUsuario();
        IControladorEvento controladorEvento = fabrica.getControladorEvento();
        
        // Luego obtener CargaDatos
        ICargaDatos cargaDatos = fabrica.getCargaDatos();
        
        assertNotNull(controladorUsuario);
        assertNotNull(controladorEvento);
        assertNotNull(cargaDatos);
    }
}


