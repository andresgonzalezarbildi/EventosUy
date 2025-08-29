package logica.controladores;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import excepciones.CategoriaRepetidaException;
import excepciones.EventoNoExisteException;
import logica.clases.Categoria;
import logica.clases.EdicionEvento;
import logica.clases.Evento;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.interfaces.IControladorEvento;
import logica.manejadores.ManejadorEvento;

public class ControladorEvento implements IControladorEvento {
	private ManejadorEvento manejadorEvento;
	
    public  ControladorEvento() {
    	manejadorEvento = ManejadorEvento.getInstance();
    }
    
	public void altaEvento(String nombre, String descripcion, String sigla, Set<String> nombresCategorias) {
		if (nombre == null || nombre.isBlank()) 
			throw new IllegalArgumentException("El nombre no puede ser vacío.");
		if (sigla == null || sigla.isBlank())
			throw new IllegalArgumentException("La sigla no puede ser vacía.");
		if (manejadorEvento.existeEvento(nombre)) {
			throw new IllegalArgumentException("Ya existe un evento con nombre: " + nombre);
		}
		
		List<Categoria> cats = new ArrayList<>();
		if (nombresCategorias != null) {
			for (String nc : nombresCategorias) {
				Categoria c = manejadorEvento.obtenerCategoria(nc);
				if (c == null) {
					throw new IllegalArgumentException("Categoría inexistente: " + nc);
				}
				cats.add(c);
			}
		}
		if (cats.isEmpty()) {
	        throw new IllegalArgumentException("El evento debe tener al menos una categoría asociada.");
	    }
		Evento e = new Evento(nombre, descripcion, sigla);
		for (Categoria c : cats) {
            e.agregarCategoria(c);
        }

		manejadorEvento.agregarEvento(e);
	}
    
   public void consultaEvento(){
	   
    
   	}
    
    //el altaCategoria es sin GUI
   public void altaCategoria(String nombre) throws CategoriaRepetidaException {
       if (nombre == null || nombre.isBlank()) {
           throw new IllegalArgumentException("El nombre de la categoría no puede ser vacío.");
       }
       if (manejadorEvento.existeCategoria(nombre)) {
           throw new CategoriaRepetidaException("Ya existe una categoría con el nombre: " + nombre);
       }
       Categoria nueva = new Categoria(nombre);
       manejadorEvento.agregarCategoria(nueva);
   }
    
    public DataEvento[] getEventosDTO() {
        return manejadorEvento.getEventosDTO(); 
    }
    
    public DataEvento[] listarEventoExistentes() throws EventoNoExisteException {
    	 Map<String,Evento> eventos = manejadorEvento.getEventos();

    	 if (eventos != null) {
    		 DataEvento[] de = new DataEvento[eventos.size()];
    		 int i = 0;
    		 for (Evento eve : eventos.values()) {
    			 de[i++] = new DataEvento(eve.getNombre(), eve.getDescripcionEvento(), eve.getSigla(), eve.getFecha(), eve.getCategoriasLista());
    		 }
    		 return de;
    	 }else {
    		 throw new EventoNoExisteException("No existen eventos registrados");
    	 }
    }
      
    
    public DataEdicion[] listarEdiciones(String nombreEvento) throws EventoNoExisteException {
    	Evento eve = manejadorEvento.obtenerEvento(nombreEvento);

    	Map<String,EdicionEvento> ediciones = eve.getEdiciones();
    	if (ediciones != null) {
    		DataEdicion[] dEdi = new DataEdicion[ediciones.size()];
    		int i=0;
    		for (EdicionEvento edi: ediciones.values()) {
    			dEdi[i++] = new DataEdicion(edi.getNombre(), edi.getFechaIni(), edi.getFechaFin(), edi.getCiudad(), edi.getPais(), edi.getSigla(), edi.getFechaAltaEnPlataforma());
    		}
    		return dEdi;
    	}else {
    		throw new EventoNoExisteException("No existen ediciones registradas del Evento");
    	}
   }
}	
