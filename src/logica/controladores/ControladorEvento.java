package logica.controladores;
import java.util.List;
import java.util.Map;

import excepciones.EventoNoExisteException;
import logica.clases.Evento;
import logica.clases.Categoria;
import logica.clases.EdicionEvento;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.interfaces.IControladorEvento;
import logica.manejadores.ManejadorEvento;

import java.time.LocalDate;

public class ControladorEvento implements IControladorEvento {
	private ManejadorEvento manejadorEvento;
	
    public  ControladorEvento() {
    	manejadorEvento = ManejadorEvento.getInstance();
    }
    
    public void altaEvento(String nombre,String descripcion,String sigla,LocalDate date,List<Categoria> categorias)  {
    		Evento nuevo = new Evento(nombre,descripcion,sigla);
        for (Categoria c : categorias) {
            nuevo.agregarCategoria(c);
        }
    };
    
//    public void consultaEvento(){
//    	}
    
    //el altaCategoria es sin GUI
    public void altaCategoria(String nombre) {
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

