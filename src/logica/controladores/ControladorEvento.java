package logica.controladores;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import excepciones.CategoriaRepetidaException;
import excepciones.EdicionNoExisteException;
import excepciones.EventoNoExisteException;
import excepciones.UsuarioNoExisteException;
import logica.clases.Asistente;
import logica.clases.Categoria;
import logica.clases.EdicionEvento;
import logica.clases.Evento;
import logica.clases.Organizador;
import logica.clases.Registro;
import logica.clases.TipoRegistro;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataRegistro;
import logica.datatypes.DataTipoRegistro;
import logica.datatypes.DataUsuario;
import logica.interfaces.IControladorEvento;
import logica.manejadores.ManejadorEvento;
import logica.manejadores.ManejadorUsuario;



public class ControladorEvento implements IControladorEvento {
	private ManejadorEvento manejadorEvento;
	
    public  ControladorEvento() { 
    	manejadorEvento = ManejadorEvento.getInstance();  
    } 
    
	public void altaEvento(String nombre, String descripcion, String sigla, List<String> nombresCategorias, LocalDate FechaAltaEnPlataforma) {
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
		Evento e = new Evento(nombre, descripcion, sigla, FechaAltaEnPlataforma);
		for (Categoria c : cats) {
            e.agregarCategoria(c);
        }

		manejadorEvento.agregarEvento(e);

	}
	
	@Override
	public DataEdicion getInfoEdicion(String idEdicion) throws EdicionNoExisteException {
	    EdicionEvento ed = manejadorEvento.getEdicion(idEdicion);
	    if (ed == null) {
	        throw new EdicionNoExisteException("No existe la edición: " + idEdicion);
	    }

	   
	    return new DataEdicion(
	        ed.getNombre(),
	        ed.getFechaIni(),
	        ed.getFechaFin(),
	        ed.getCiudad(),
	        ed.getPais(),
	        ed.getSigla(),
	        ed.getFechaAltaEnPlataforma(),
	        ed.getOrganizadorDTO(),
	        ed.getTiposRegistroDTO(),
	        ed.getPatrociniosDTO());
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
	    Collection<Evento> evs = manejadorEvento.obtenerTodosEventos();

	    List<DataEvento> lista = new ArrayList<>(evs.size());
	    for (Evento e : evs) {
	        lista.add(new DataEvento(
	            e.getNombre(),
	            e.getDescripcionEvento(),
	            e.getSigla(),
	            e.getFecha(),
	            e.getCategoriasLista()
	        ));
	    }

	    // Ordenamos por nombre, ignorando mayúsculas/minúsculas
	    lista.sort(Comparator.comparing(DataEvento::getNombre, String.CASE_INSENSITIVE_ORDER));

	    return lista.toArray(new DataEvento[0]);
	}

   
   
   
    public DataEvento[] listarEventoExistentes() throws EventoNoExisteException {
    	 Map<String,Evento> eventos = manejadorEvento.getEventos();

    	 if (eventos != null) {
    		 DataEvento[] de = new DataEvento[eventos.size()];
    		 int i = 0;
    		 for (Evento eve : eventos.values()) {
    			 de[i++] = new DataEvento(eve.getNombre(), eve.getDescripcionEvento(), eve.getSigla(), eve.getFecha(), eve.getCategoriasLista());
    		 }
    		Arrays.sort(de, Comparator.comparing(DataEvento::getNombre));

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
    		Arrays.sort(dEdi, Comparator.comparing(DataEdicion::getNombre));
    		return dEdi;

    	}else {
    		throw new EventoNoExisteException("No existen ediciones registradas del Evento");
    	}
   }
    
    public void altaEdicionEvento(
            String nombreEvento, String nombreEdicion, String sigla, String ciudad, String pais, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAltaEnPlataforma, String organizadorNick) throws UsuarioNoExisteException {
        if (nombreEvento == null || nombreEvento.isBlank())   
        	throw new IllegalArgumentException("El evento es obligatorio.");
        if (organizadorNick == null || organizadorNick.isBlank())   
        	throw new IllegalArgumentException("El organizador es obligatorio.");
        if (nombreEdicion == null || nombreEdicion.isBlank())  
        	throw new IllegalArgumentException("El nombre de la edición es obligatorio.");
        if (sigla == null || sigla.isBlank())          
        	throw new IllegalArgumentException("La sigla es obligatoria.");
        if (ciudad == null || ciudad.isBlank())         
        	throw new IllegalArgumentException("La ciudad es obligatoria.");
        if (pais == null || pais.isBlank())           
        	throw new IllegalArgumentException("El país es obligatorio.");
        if (fechaInicio == null || fechaFin == null || fechaAltaEnPlataforma == null)
        	throw new IllegalArgumentException("Las fechas de Inicio ,Fin y Alta son obligatorias.");
        if (fechaFin.isBefore(fechaInicio))
        	throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la de inicio.");
        if (fechaInicio.isBefore(fechaAltaEnPlataforma))
        	throw new IllegalArgumentException("La fecha de Alta de la edición no puede ser posterior a la fecha de de inicio de la edición.");

        Evento evento = manejadorEvento.obtenerEvento(nombreEvento);
        if (evento == null) {
            throw new IllegalArgumentException("No existe el evento: " + nombreEvento);
        }
        LocalDate FechaEvento = evento.getFecha();
        if (fechaAltaEnPlataforma.isBefore(FechaEvento))
        	throw new IllegalArgumentException(
        	        "La fecha de inicio de la edición a crear no puede ser posterior a la fecha de alta en plataforma del evento \"" 
        	                + evento.getNombre() + "\""
        	            );
        if (evento.getEdicion(nombreEdicion) != null) {
            throw new IllegalArgumentException("Ya existe una edición con ese nombre en este evento: " + nombreEvento);
        }
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        Organizador org = manejadorUsuario.getOrganizador(organizadorNick);
        if (org == null) {
            throw new IllegalArgumentException("Organizador inexistente: " + organizadorNick);
        }
        
        EdicionEvento ed = new EdicionEvento( nombreEdicion, fechaInicio, fechaFin, ciudad, pais, sigla, (fechaAltaEnPlataforma != null ? fechaAltaEnPlataforma : LocalDate.now())
        );
        ed.setOrganizador(org);
        ed.setEventoPadre(evento);
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
    
    public void altaRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro, String nombreAsistente, LocalDate fecha) throws UsuarioNoExisteException {
        Evento ev = ManejadorEvento.getInstance().obtenerEvento(nombreEvento);
        if (ev == null) throw new IllegalArgumentException("No existe el evento: " + nombreEvento);

        EdicionEvento ed = ev.getEdicion(nombreEdicion);
        if (ed == null) throw new IllegalArgumentException("No existe la edición: " + nombreEdicion);

        TipoRegistro tipo = ed.getTipoDeRegistro(nombreTipoRegistro);
        if (tipo == null) throw new IllegalArgumentException("No existe el tipo de registro: " + nombreTipoRegistro);

        Asistente asis = ManejadorUsuario.getInstance().getAsistente(nombreAsistente);
        if (asis == null) throw new IllegalArgumentException("No existe el asistente: " + nombreAsistente);

        if (!ed.hayCupo(tipo)) {
            throw new IllegalStateException("No hay cupo disponible para el tipo de registro: " + nombreTipoRegistro);
        }
        if (ed.estaRegistrado(asis)) {
            throw new IllegalStateException("El asistente ya está registrado en esta edición.");
        }
        LocalDate FechaAltaEdi = ed.getFechaAltaEnPlataforma();
        if (fecha.isBefore(FechaAltaEdi))
        	throw new IllegalArgumentException("La fecha de Registro no puede ser anterior al de Alta de la edición.");
        LocalDate FechaFinEdi = ed.getFechaFin();
        if (FechaFinEdi.isBefore(fecha))
        	throw new IllegalArgumentException("La fecha de Registro no puede ser poserior al fin de la edición.");

        

        int costo = tipo.getCosto();
        Registro reg = new Registro(fecha , costo, tipo, ed, asis);

        ed.agregarRegistro(reg);
        asis.agregarRegistro(reg);
    }



	public DataTipoRegistro[] listarTiposRegistro(String nombreEvento, String nombreEdicion) {
    Evento ev = manejadorEvento.obtenerEvento(nombreEvento);
    if (ev == null) throw new IllegalArgumentException("No existe el evento: " + nombreEvento);

    EdicionEvento edi = ev.getEdicion(nombreEdicion);
    if (edi == null) throw new IllegalArgumentException("No existe la edición: " + nombreEdicion);

    DataTipoRegistro[] lista = edi.getTiposRegistroDTO().toArray(new DataTipoRegistro[0]);
    Arrays.sort(lista, Comparator.comparing(DataTipoRegistro::getNombre));
    return lista;


}


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
	}

	public DataRegistro[] listarRegistrosDeUsuario(String nickname) {
	    List<DataRegistro> out = new ArrayList<>();
	    for (Evento e : manejadorEvento.getEventos().values()) {
	        for (EdicionEvento ed : e.getEdiciones().values()) {
	            for (Registro r : ed.getRegistros()) {
	                if (nickname.equals(r.getAsistente().getNickname())) {
	                    out.add(new DataRegistro(
	                        e.getNombre(),
	                        ed.getNombre(),
	                        r.getTipoRegistro().getNombre(),
	                        r.getCostoRegistro(),
	                        r.getFechaRegistro(),
	                        r.getAsistente().getNickname()
	                    ));
	                }
	            }
	        }
	    }
	    return out.toArray(new DataRegistro[0]);
	}
	
	public void limpiar() {
		manejadorEvento.limpiar();
	}
}
