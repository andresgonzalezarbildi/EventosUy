package logica.interfaces;
import java.util.Set;
import java.util.List;

import excepciones.CategoriaRepetidaException;
import excepciones.EventoNoExisteException;
import logica.datatypes.DataEvento;

public interface IControladorEvento {
	public void altaEvento(String nombre, String descripcion, String sigla, List<String> nombresCategorias) ;
    public void altaCategoria(String nombre) throws CategoriaRepetidaException;
    public DataEvento[] getEventosDTO() ;
    public DataEvento[] listarEventoExistentes() throws EventoNoExisteException;
}