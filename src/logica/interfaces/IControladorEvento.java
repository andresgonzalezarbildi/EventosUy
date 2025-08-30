package logica.interfaces;
import java.time.LocalDate;
import java.util.List;

import excepciones.CategoriaRepetidaException;
import excepciones.EventoNoExisteException;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataTipoRegistro;

public interface IControladorEvento {
	public void altaEvento(String nombre, String descripcion, String sigla, List<String> nombresCategorias) ;
	public void altaEdicionEvento(String nombreEvento, String nombreEdicion, String sigla, String ciudad, String pais, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAltaEnPlataforma, String organizadorNick);

	public void altaTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro, String descripcion, Integer costo,  Integer cupos);

    public void altaCategoria(String nombre) throws CategoriaRepetidaException;
    public DataEvento[] getEventosDTO() ;
    public DataEvento[] listarEventoExistentes() throws EventoNoExisteException;
    public DataEdicion[] listarEdiciones(String nombreEvento) throws EventoNoExisteException;
    public DataTipoRegistro[] listarTiposRegistro(String nombreEvento, String nombreEdicion);
    public DataTipoRegistro getTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTipo);
}