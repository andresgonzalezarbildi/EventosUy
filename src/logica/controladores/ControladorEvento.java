package logica.controladores;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import excepciones.CategoriaRepetidaException;
import excepciones.EventoNoExisteException;
import logica.clases.Categoria;
import logica.clases.EdicionEvento;
import logica.clases.Evento;
import logica.clases.Organizador;
import logica.clases.TipoRegistro;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataTipoRegistro;
import logica.interfaces.IControladorEvento;
import logica.manejadores.ManejadorEvento;
import logica.manejadores.ManejadorUsuario;

public class ControladorEvento implements IControladorEvento {
	private ManejadorEvento manejadorEvento;
	
    public  ControladorEvento() {
    	manejadorEvento = ManejadorEvento.getInstance();
    }
    
	public void altaEvento(String nombre, String descripcion, String sigla, List<String> nombresCategorias) {
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
	    DataEvento[] eventos = manejadorEvento.getEventosDTO();
	    if (eventos == null) {
	        System.out.println("getEventosDTO devolvió null");
	    } else {
	        System.out.println("getEventosDTO devolvió " + eventos.length + " eventos");
	        for (DataEvento ev : eventos) {
	            if (ev != null) {
	                System.out.println("  -> " + ev.getNombre());
	            } else {
	                System.out.println("  ⚠️ Evento nulo en array");
	            }
	        }
	    }
	    return eventos;
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
    		int i = 0;
    		for (EdicionEvento edi : ediciones.values()) {
    			dEdi[i++] = new DataEdicion(
    				    edi.getNombre(),
    				    edi.getFechaIni(),
    				    edi.getFechaFin(),
    				    edi.getCiudad(),
    				    edi.getPais(),
    				    edi.getSigla(),
    				    edi.getFechaAltaEnPlataforma(),
    				    edi.getOrganizadorDTO(),   
    				    edi.getTiposRegistroDTO(),  
    				    edi.getPatrociniosDTO()       
    				);

    		}
    		
    		return dEdi;
    	}else {
    		throw new EventoNoExisteException("No existen ediciones registradas del Evento");
    	}
   }
    
    public void altaEdicionEvento(
            String nombreEvento, String nombreEdicion, String sigla, String ciudad, String pais, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAltaEnPlataforma, String organizadorNick) {
        if (nombreEvento == null || nombreEvento.isBlank())   
        	throw new IllegalArgumentException("El evento es obligatorio.");
        if (nombreEdicion == null || nombreEdicion.isBlank())  
        	throw new IllegalArgumentException("El nombre de la edición es obligatorio.");
        if (sigla == null || sigla.isBlank())          
        	throw new IllegalArgumentException("La sigla es obligatoria.");
        if (ciudad == null || ciudad.isBlank())         
        	throw new IllegalArgumentException("La ciudad es obligatoria.");
        if (pais == null || pais.isBlank())           
        	throw new IllegalArgumentException("El país es obligatorio.");
        if (fechaInicio == null || fechaFin == null)
        	throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias.");
        if (fechaFin.isBefore(fechaInicio))
        	throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la de inicio.");
        Evento evento = manejadorEvento.obtenerEvento(nombreEvento);
        if (evento == null) {
            throw new IllegalArgumentException("No existe el evento: " + nombreEvento);
        }
        if (evento.getEdicion(nombreEdicion) != null) {
            throw new IllegalArgumentException("Ya existe una edición con ese nombre en este evento: " + nombreEvento);
        }
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        Organizador org = manejadorUsuario.obtenerOrganizadorPorNickname(organizadorNick);
        if (org == null) {
            throw new IllegalArgumentException("Organizador inexistente: " + organizadorNick);
        }
        
        EdicionEvento ed = new EdicionEvento( nombreEdicion, fechaInicio, fechaFin, ciudad, pais, sigla, (fechaAltaEnPlataforma != null ? fechaAltaEnPlataforma : LocalDate.now())
        );
        org.agregarEdicion(ed);
        evento.agregarEdicion(ed);
    }

    public void altaTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro, String descripcion, Integer costo,  Integer cupos) {
    	if (nombreEvento == null || nombreEvento.isBlank())   
        	throw new IllegalArgumentException("El evento es obligatorio.");
        if (nombreEdicion == null || nombreEdicion.isBlank())  
        	throw new IllegalArgumentException("El nombre de la edición es obligatorio.");
        if (nombreTipoRegistro == null || nombreTipoRegistro.isBlank())          
        	throw new IllegalArgumentException("El nombre del Tipo Registro es obligatoria.");
        if (descripcion == null || descripcion.isBlank())         
        	throw new IllegalArgumentException("La ciudad es obligatoria.");
        if (cupos == null || cupos <= 0)
            throw new IllegalArgumentException("El cupo debe ser un número positivo mayor que cero.");
        if (costo == null || costo < 0)
            throw new IllegalArgumentException("El costo debe ser un número positivo mayor que cero.");
        Evento evento = manejadorEvento.obtenerEvento(nombreEvento);
        if (evento == null) {
            throw new IllegalArgumentException("No existe el evento: " + nombreEvento);
        }
        EdicionEvento edicion = evento.getEdicion(nombreEdicion);
        if (edicion == null) {
            throw new IllegalArgumentException("No existe la edicion del evento: " + nombreEvento);
        }
        if (edicion.existeTipoDeRegistro(nombreTipoRegistro)) {
            throw new IllegalArgumentException("Ya existe un tipo registro con nombre: " + nombreEdicion);
        }
        TipoRegistro tipoRegistronuevo = new TipoRegistro(nombreTipoRegistro, descripcion, costo, cupos);
        edicion.agregarTipoDeRegistro(nombreTipoRegistro, tipoRegistronuevo);
    }
    


    @Override
	public DataTipoRegistro[] listarTiposRegistro(String nombreEvento, String nombreEdicion) {
    Evento ev = manejadorEvento.obtenerEvento(nombreEvento);
    if (ev == null) throw new IllegalArgumentException("No existe el evento: " + nombreEvento);

    EdicionEvento edi = ev.getEdicion(nombreEdicion);
    if (edi == null) throw new IllegalArgumentException("No existe la edición: " + nombreEdicion);

    return edi.getTiposRegistroDTO().toArray(new DataTipoRegistro[0]);

}

	@Override
	public DataTipoRegistro getTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTipo) {
    Evento ev = manejadorEvento.obtenerEvento(nombreEvento);
    if (ev == null) throw new IllegalArgumentException("No existe el evento: " + nombreEvento);

    EdicionEvento edi = ev.getEdicion(nombreEdicion);
    if (edi == null) throw new IllegalArgumentException("No existe la edición: " + nombreEdicion);

    TipoRegistro tr = edi.getTipoDeRegistro(nombreTipo);
    if (tr == null) throw new IllegalArgumentException("No existe el tipo de registro: " + nombreTipo);

    return new DataTipoRegistro(
            tr.getNombre(),
            tr.getDescripcion(),
            tr.getCosto(),
            tr.getCupo()
    );
	}}
