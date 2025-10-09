package src.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.UsuarioNoExisteException;
import logica.CargaDatos.CargaDatos;
import logica.controladores.ControladorEvento;
import logica.controladores.ControladorUsuario;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataTipoRegistro;
import logica.datatypes.DataUsuario;

class CargaDatosTest {

	private ControladorUsuario cu;
	private ControladorEvento ce;
	private CargaDatos carga;

	@BeforeEach
	void setUp() {
		cu = ControladorUsuario.getInstance();
		ce = new ControladorEvento();

		// Limpiar estado global antes de cada test
		cu.limpar();
		ce.limpiar();

		carga = new CargaDatos(cu, ce);
		carga.CargarDatosIniciales();
	}

	@Test
	void testUsuariosCargados() throws UsuarioNoExisteException {
		DataUsuario org = cu.verInfoUsuario("miseventos");
		assertNotNull(org);
		assertEquals("Organizador", org.getTipo());

		DataUsuario asis = cu.verInfoUsuario("msilva");
		assertNotNull(asis);
		assertEquals("Asistente", asis.getTipo());
		assertEquals(LocalDate.of(1987, 8, 21), asis.getFechaNacimiento());
	}

	@Test
	void testEventosYEdicionesCargados() throws Exception {
		DataEvento[] eventos = ce.getEventosDTO();
		assertNotNull(eventos);
		assertTrue(eventos.length >= 5);

		// Chequear que existan ediciones para un evento conocido
		DataEdicion[] eds = ce.listarEdiciones("Montevideo Comics");
		assertNotNull(eds);
		assertTrue(eds.length >= 1);
	}

	@Test
	void testTiposRegistroCargados() {
		DataTipoRegistro[] tipos = ce.listarTiposRegistro("Montevideo Rock", "Montevideo Rock 2025");
		assertNotNull(tipos);
		assertTrue(tipos.length >= 2);
	}

	@Test
	void testAltaRegistroConDatosCargados() throws Exception {
		// Registrar a msilva en Montevideo Rock 2025 General
		ce.altaRegistro("Montevideo Rock", "Montevideo Rock 2025", "General", "msilva", LocalDate.now());
		assertEquals(1, ce.listarRegistrosDeUsuario("msilva").length);
	}

	@Test
	void testCargaIdempotente() {
		int eventosAntes = ce.getEventosDTO().length;
		assertDoesNotThrow(() -> carga.CargarDatosIniciales());
		int eventosDespues = ce.getEventosDTO().length;
		assertEquals(eventosAntes, eventosDespues);
	}
}


