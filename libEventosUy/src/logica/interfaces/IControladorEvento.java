package logica.interfaces;
import java.time.LocalDate;
import java.util.List;

import excepciones.CategoriaRepetidaException;
import excepciones.EventoNoExisteException;
import excepciones.TransicionEstadoInvalidaException;
import excepciones.UsuarioNoExisteException;
import excepciones.EdicionNoExisteException;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataRegistro;
import logica.datatypes.DataTipoRegistro;

public interface IControladorEvento {
	public void altaEvento(String nombre, String descripcion, String sigla, List<String> nombresCategorias, LocalDate FechaAltaEnPlataforma, String imagen) ;
	public void altaEdicionEvento(String nombreEvento, String nombreEdicion, String sigla, String ciudad, String pais, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAltaEnPlataforma, String organizadorNick) throws UsuarioNoExisteException;
	public void altaEdicionEvento(String nombreEvento, String nombreEdicion, String sigla, String ciudad, String pais, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAltaEnPlataforma, String organizadorNick, String imagen) throws UsuarioNoExisteException;

	public void altaTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro, String descripcion, Integer costo,  Integer cupos);
	void altaRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro, String nombreAsistente, LocalDate fecha) throws UsuarioNoExisteException;
    public void altaCategoria(String nombre) throws CategoriaRepetidaException;
    public DataEvento[] getEventosDTO() ;
    public DataEvento getUnEventoDTO(String nombre) ;
    public DataEvento[] listarEventoExistentes() throws EventoNoExisteException;
   
    public DataTipoRegistro[] listarTiposRegistro(String nombreEvento, String nombreEdicion);
    public DataTipoRegistro getTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTipo);
	public DataEdicion getInfoEdicion(String idEdicion) throws EdicionNoExisteException ;
    public DataRegistro[] listarRegistrosDeUsuario(String nickname) ;
    
    public void aceptarEdicion(String nombreEdicion, Boolean aceptada) throws EdicionNoExisteException, TransicionEstadoInvalidaException;

    public DataEdicion[] listarEdiciones(String nombreEvento) throws EventoNoExisteException;
    public DataEdicion[] listarEdicionesAceptadas() throws EdicionNoExisteException;
    public DataEdicion[] listarEdicionesIngresadas() throws EdicionNoExisteException;
    public DataEdicion[] listarEdicionesRechazadas() throws EdicionNoExisteException;
    
    public void  setCostoRegistro(String nickname,String edicion, String nombreTipo, int precio); 
    
}