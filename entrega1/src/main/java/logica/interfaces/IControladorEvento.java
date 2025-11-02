package logica.interfaces;
import java.time.LocalDate;
import java.util.List;

import excepciones.CategoriaRepetidaException;
import excepciones.EdicionNoExisteException;
import excepciones.EventoConEdicionesPedientesException;
import excepciones.EventoNoExisteException;
import excepciones.EventoRepetidoException;
import excepciones.TipoRegistroRepetidoException;
import excepciones.TransicionEstadoInvalidaException;
import excepciones.UsuarioNoExisteException;
import excepciones.EdicionSinComenzarException;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataRegistro;
import logica.datatypes.DataTipoRegistro;

public interface IControladorEvento {
	public void altaEvento(String nombre, String descripcion, String sigla, List<String> nombresCategorias, LocalDate FechaAltaEnPlataforma, String imagen) throws EventoRepetidoException;
	public void altaEdicionEvento(String nombreEvento, String nombreEdicion, String sigla, String ciudad, String pais, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAltaEnPlataforma, String organizadorNick) throws UsuarioNoExisteException;
	public void altaEdicionEvento(String nombreEvento, String nombreEdicion, String sigla, String ciudad, String pais, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAltaEnPlataforma, String organizadorNick, String imagen) throws UsuarioNoExisteException;

	public void altaTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro, String descripcion, Integer costo,  Integer cupos) throws TipoRegistroRepetidoException ;
	public void altaRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro, String nombreAsistente, LocalDate fecha) throws UsuarioNoExisteException;
    public void altaCategoria(String nombre) throws CategoriaRepetidaException;
    
    public DataEvento[] getEventosDTO() ;
    public DataEvento getUnEventoDTO(String nombre) throws EventoNoExisteException;
    public DataEvento[] listarEventoExistentes() throws EventoNoExisteException;
   
    public DataTipoRegistro[] listarTiposRegistro(String nombreEvento, String nombreEdicion);
    public DataTipoRegistro getTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTipo);
	

    public DataRegistro[] listarRegistrosDeEdicion(String nombreEdicion);
    public DataRegistro[] listarRegistrosDeUsuario(String nickname);
    public DataRegistro listarUnRegistroDeUsuario(String nombreEdicion, String nickname) ;
    

    public String getEventoDeUnaEdicion(String nombreEdicion);
    public DataEdicion getInfoEdicion(String idEdicion) throws EdicionNoExisteException ;
    public DataEdicion[] listarEdiciones(String nombreEvento) throws EventoNoExisteException;
    public DataEdicion[] listarEdicionesOrganizador(String nombreOrganizador) throws EdicionNoExisteException;
    public DataEdicion[] listarEdicionesAceptadas() throws EdicionNoExisteException;
    public DataEdicion[] listarEdicionesAceptadasEvento(String nombreEvento) throws EdicionNoExisteException , EventoNoExisteException;
    public DataEdicion[] listarEdicionesOrganizadorAceptadas(String nombreOrganizador) throws EdicionNoExisteException;
    public DataEdicion[] listarEdicionesIngresadas() throws EdicionNoExisteException;
    public DataEdicion[] listarEdicionesIngresadasEvento(String nombreEvento) throws EdicionNoExisteException;
    public DataEdicion[] listarEdicionesRechazadas() throws EdicionNoExisteException;
    public DataEdicion[] listarEdicionesRechazadasEvento(String nombreEvento) throws EdicionNoExisteException;

    public void aceptarEdicion(String nombreEdicion, Boolean aceptada) throws EdicionNoExisteException, TransicionEstadoInvalidaException;
    public void  setCostoRegistro(String nickname, String edicion, String nombreTipo, int precio); 
    
    public void confirmarAsistencia(String nombreEdicion, String nickname) throws EdicionSinComenzarException;
    public void finalizarEvento(String nombreEvento) throws EventoConEdicionesPedientesException;

    public List<String> listarCategorias();
    
}