package src.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.CargaDatos.CargaDatos;
import logica.interfaces.IControladorUsuario;
import logica.interfaces.IControladorEvento;
import logica.datatypes.DataUsuario;
import logica.datatypes.DataEvento;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataTipoRegistro;

class SistemaTest {

    private IControladorUsuario cu;
    private IControladorEvento ce;
    private CargaDatos carga;

    @BeforeEach
    void setUp() {
        // Aquí deberías inicializar tus controladores reales, no mocks
        // Por ejemplo: cu = new ControladorUsuario(); ce = new ControladorEvento();
        carga = new CargaDatos(cu, ce);
        carga.CargarDatosIniciales();
    }

    @Test
    void testUsuariosCargados() throws UsuarioNoExisteException {
        // Verificar que usuarios "fake" de CargaDatos existen
        DataUsuario u = cu.verInfoUsuario("miseventos");
        assertNotNull(u);
        assertEquals("Organizador", u.getTipo());

        u = cu.verInfoUsuario("msilva");
        assertNotNull(u);
        assertEquals("Asistente", u.getTipo());
        assertEquals(LocalDate.of(1987, 8, 21), u.getFechaNacimiento());
    }

    @Test
    void testAltaUsuarioRepetido() {
        // Debe lanzar excepción si se intenta crear un usuario con mismo nickname
        assertThrows(UsuarioRepetidoException.class, () -> {
            cu.altaUsuario("msilva", "Martin", "martin.silva@fing.edu.uy", "Asistente", null, null, "Silva", LocalDate.of(1987, 8, 21));
        });
    }

    @Test
    void testEventosCargados() throws Exception {
        DataEvento[] eventos = ce.getEventosDTO();
        assertTrue(eventos.length > 0);

        DataEvento e = eventos[0];
        assertNotNull(e.getNombre());
        DataEdicion[] eds = ce.listarEdiciones(e.getNombre());
        assertNotNull(eds);
        assertTrue(eds.length > 0);

        DataTipoRegistro[] tipos = ce.listarTiposRegistro(e.getNombre(), eds[0].getNombre());
        assertNotNull(tipos);
        assertTrue(tipos.length > 0);
    }

    @Test
    void testAltaRegistro() throws UsuarioNoExisteException {
        // Crear un registro simple de asistente
        ce.altaRegistro("Montevideo Rock", "Montevideo Rock 2025", "General", "msilva", LocalDate.now());

        assertEquals(1, ce.listarRegistrosDeUsuario("msilva").length);
    }
}
