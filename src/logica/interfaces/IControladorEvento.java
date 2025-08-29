package logica.interfaces;
import java.util.Set;

import excepciones.EventoNoExisteException;
import logica.datatypes.DataEvento;

public interface IControladorEvento {
	public void altaEvento(String nombre, String descripcion, String sigla, Set<String> nombresCategorias) ;
    public void altaCategoria(String nombre);
    public DataEvento[] getEventosDTO() ;
    public DataEvento[] listarEventoExistentes() throws EventoNoExisteException;
}