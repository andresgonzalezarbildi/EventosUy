package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.UsuarioNoExisteException;
import logica.cargadatos.CargaDatos;
import logica.controladores.ControladorEvento;
import logica.controladores.ControladorUsuario;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataTipoRegistro;
import logica.datatypes.DataUsuario;

class CargaDatosTest {

	private ControladorUsuario controladorUsuario;
	private ControladorEvento controladorEvento;
	private CargaDatos carga;

	@BeforeEach
	void setUp() {
		controladorUsuario = ControladorUsuario.getInstance();
		controladorEvento = new ControladorEvento();

		// Limpiar estado global antes de cada test
		controladorUsuario.limpar();
		controladorEvento.limpiar();

		carga = new CargaDatos(controladorUsuario, controladorEvento);
		carga.cargarDatosIniciales();
	}

	@Test
	void testUsuariosCargados() throws UsuarioNoExisteException {
		DataUsuario org = controladorUsuario.verInfoUsuario("miseventos");
		assertNotNull(org);
		assertEquals("Organizador", org.getTipo());

		DataUsuario asis = controladorUsuario.verInfoUsuario("msilva");
		assertNotNull(asis);
		assertEquals("Asistente", asis.getTipo());
		assertEquals(LocalDate.of(1987, 8, 21), asis.getFechaNacimiento());
	}

	@Test
	void testEventosYEdicionesCargados() throws Exception {
		DataEvento[] eventos = controladorEvento.getEventosDTO();
		assertNotNull(eventos);
		assertTrue(eventos.length >= 5);

		// Chequear que existan ediciones para un evento conocido
		DataEdicion[] eds = controladorEvento.listarEdiciones("Montevideo Comics");
		assertNotNull(eds);
		assertTrue(eds.length >= 1);
	}

	@Test
	void testTiposRegistroCargados() {
		DataTipoRegistro[] tipos = controladorEvento.listarTiposRegistro("Montevideo Rock", "Montevideo Rock 2025");
		assertNotNull(tipos);
		assertTrue(tipos.length >= 2);
	}

	@Test
	void testAltaRegistroConDatosCargados() throws Exception {
		// Registrar a msilva en Montevideo Rock 2025 General
		controladorEvento.altaRegistro("Montevideo Rock", "Montevideo Rock 2025", "General", "msilva", LocalDate.now());
		assertEquals(1, controladorEvento.listarRegistrosDeUsuario("msilva").length);
	}

	@Test
	void testCargaIdempotente() {
		int eventosAntes = controladorEvento.getEventosDTO().length;
		assertDoesNotThrow(() -> carga.cargarDatosIniciales());
		int eventosDespues = controladorEvento.getEventosDTO().length;
		assertEquals(eventosAntes, eventosDespues);
	}
}


