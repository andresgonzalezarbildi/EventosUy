package logica.interfaces;
import java.util.Set;
import java.time.LocalDate;
import java.util.List;

import excepciones.CategoriaRepetidaException;
import excepciones.EventoNoExisteException;
import logica.datatypes.DataEvento;

public interface IControladorEvento {
	public void altaEvento(String nombre, String descripcion, String sigla, List<String> nombresCategorias) ;
	public void altaEdicionEvento(String nombreEvento, String nombreEdicion, String sigla, String ciudad, String pais, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAltaEnPlataforma, String organizadorNick);
    public void altaCategoria(String nombre) throws CategoriaRepetidaException;
    public DataEvento[] getEventosDTO() ;
    public DataEvento[] listarEventoExistentes() throws EventoNoExisteException;
}