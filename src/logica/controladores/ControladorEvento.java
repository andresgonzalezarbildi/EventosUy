package logica.controladores;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import excepciones.UsuarioNoExisteException;
import logica.clases.Evento;
import logica.clases.Categoria;
import logica.datatypes.DataEvento;
import logica.datatypes.DataUsuario;
import logica.interfaces.IControladorEvento;
import logica.manejadores.ManejadorEvento;

import java.time.LocalDate;

public class ControladorEvento implements IControladorEvento {
	private ManejadorEvento manejador;
    public  ControladorEvento() {
     manejador = ManejadorEvento.getInstance();
    }
    public void altaEvento(String nombre,String descripcion,String sigla,LocalDate date,List<Categoria> categorias)  {
    		Evento nuevo = new Evento(nombre,descripcion,sigla);
        for (Categoria c : categorias) {
            nuevo.agregarCategoria(c);
        }
        manejador.agregarEvento(nuevo);
    };
    
    public void altaCategoria(String nombre) {
    		Categoria nueva = new Categoria(nombre);
    		manejador.agregarCategoria(nueva);
    }
    
    public DataEvento[] getEventosDTO() {
        return manejador.getEventosDTO();  // llama al método del manejador
    }
}	