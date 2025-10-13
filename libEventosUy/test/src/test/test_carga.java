package src.test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.cargadatos.CargaDatos;
import logica.interfaces.IControladorUsuario;
import logica.interfaces.IControladorEvento;
import logica.datatypes.DataUsuario;
import logica.datatypes.DataEvento;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataTipoRegistro;

class SistemaTest {

    private IControladorUsuario controladorUsuario;
    private IControladorEvento controladorEvento;
    private CargaDatos carga;

    @BeforeEach
    void setUp() {
        // Aquí deberías inicializar tus controladores reales, no mocks
        // Por ejemplo: cu = new ControladorUsuario(); ce = new ControladorEvento();
        carga = new CargaDatos(controladorUsuario, controladorEvento);
        carga.cargarDatosIniciales();
    }

    @Test
    void testUsuariosCargados() throws UsuarioNoExisteException {
        // Verificar que usuarios "fake" de CargaDatos existen
        DataUsuario usuario = controladorUsuario.verInfoUsuario("miseventos");
        assertNotNull(usuario);
        assertEquals("Organizador", usuario.getTipo());

        usuario = controladorUsuario.verInfoUsuario("msilva");
        assertNotNull(usuario);
        assertEquals("Asistente", usuario.getTipo());
        assertEquals(LocalDate.of(1987, 8, 21), usuario.getFechaNacimiento());
    }

    @Test
    void testAltaUsuarioRepetido() {
        // Debe lanzar excepción si se intenta crear un usuario con mismo nickname
        assertThrows(UsuarioRepetidoException.class, () -> {
            controladorUsuario.altaUsuario("msilva", "Martin", "martin.silva@fing.edu.uy", "PerfilSinFoto.png", "password", "Asistente", null, null, "Silva", LocalDate.of(1987, 8, 21));
        });
    }

    @Test
    void testEventosCargados() throws Exception {
        DataEvento[] eventos = controladorEvento.getEventosDTO();
        assertTrue(eventos.length > 0);

        DataEvento evento = eventos[0];
        assertNotNull(evento.getNombre());
        DataEdicion[] eds = controladorEvento.listarEdiciones(evento.getNombre());
        assertNotNull(eds);
        assertTrue(eds.length > 0);

        DataTipoRegistro[] tipos = controladorEvento.listarTiposRegistro(evento.getNombre(), eds[0].getNombre());
        assertNotNull(tipos);
        assertTrue(tipos.length > 0);
    }

    @Test
    void testAltaRegistro() throws UsuarioNoExisteException {
        // Crear un registro simple de asistente
        controladorEvento.altaRegistro("Montevideo Rock", "Montevideo Rock 2025", "General", "msilva", LocalDate.now());

        assertEquals(1, controladorEvento.listarRegistrosDeUsuario("msilva").length);
    }
}
