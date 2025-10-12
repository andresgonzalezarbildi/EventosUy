package logica.controladores;

import excepciones.CategoriaRepetidaException;
import excepciones.EdicionNoExisteException;
import excepciones.EventoNoExisteException;
import excepciones.EventoRepetidoException;
import excepciones.TipoRegistroRepetidoException;
import excepciones.TransicionEstadoInvalidaException;
import excepciones.UsuarioNoExisteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import logica.clases.Asistente;
import logica.clases.Categoria;
import logica.clases.EdicionEvento;
import logica.clases.EstadoEdicion;
import logica.clases.Evento;
import logica.clases.Organizador;
import logica.clases.Registro;
import logica.clases.TipoRegistro;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataRegistro;
import logica.datatypes.DataTipoRegistro;
import logica.interfaces.IControladorEvento;
import logica.manejadores.ManejadorEvento;
import logica.manejadores.ManejadorUsuario;



public class ControladorEvento implements IControladorEvento {
  private ManejadorEvento manejadorEvento;
	
  public  ControladorEvento() { 
    manejadorEvento = ManejadorEvento.getInstance();  
  } 
    
  public void altaEvento(String nombre, String descripcion, String sigla, 
      List<String> nombresCategorias, LocalDate fechaAltaEnPlataforma, String imagen) 
          throws EventoRepetidoException {
    if (nombre == null || nombre.isBlank()) {
      throw new IllegalArgumentException("El nombre no puede ser vacío.");
    }
    if (sigla == null || sigla.isBlank()) {
      throw new IllegalArgumentException("La sigla no puede ser vacía.");
    }
    if (manejadorEvento.existeEvento(nombre)) {
      throw new EventoRepetidoException("Ya existe un evento con nombre: " + nombre);
    }
    
    List<Categoria> cats = new ArrayList<>();
    if (nombresCategorias != null) {
      for (String nc : nombresCategorias) {
        Categoria categoria = manejadorEvento.obtenerCategoria(nc);
        if (categoria == null) {
          throw new IllegalArgumentException("Categoría inexistente: " + nc);
        }
        cats.add(categoria);
      }
    }
    if (cats.isEmpty()) {
      throw new IllegalArgumentException("El evento debe tener al menos una categoría asociada.");
    }	
    Evento evento = new Evento(nombre, descripcion, sigla, fechaAltaEnPlataforma, imagen);
    for (Categoria c : cats) {
      evento.agregarCategoria(c);
    }
    manejadorEvento.agregarEvento(evento);
  }
	
  @Override
  public DataEdicion getInfoEdicion(String idEdicion) throws EdicionNoExisteException {
	  EdicionEvento edicion = manejadorEvento.getEdicion(idEdicion);
    if (edicion == null) {
      throw new EdicionNoExisteException("No existe la edición: " + idEdicion);
    }
    return new DataEdicion(
      edicion.getNombre(),
      edicion.getFechaIni(),
      edicion.getFechaFin(),
      edicion.getCiudad(),
      edicion.getPais(),
      edicion.getSigla(),
      edicion.getFechaAltaEnPlataforma(),
      edicion.getOrganizadorDTO(),
      edicion.getTiposRegistroDTO(),
      edicion.getPatrociniosDTO(),
      edicion.getImagen(),
      edicion.getEstado().name().toLowerCase(),
      edicion.getEvento().getNombre()
        );
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
              e.getCategoriasLista(),
              e.getImagen()
          ));
      }
  
      // Ordenamos por nombre, ignorando mayúsculas/minúsculas
  	    lista.sort(Comparator.comparing(DataEvento::getNombre, String.CASE_INSENSITIVE_ORDER));
  
  	    return lista.toArray(new DataEvento[0]);
  	}
  
  public DataEvento getUnEventoDTO(String nombre) throws EventoNoExisteException {
      Collection<Evento> evs = manejadorEvento.obtenerTodosEventos();
      for (Evento e : evs) {
          if (e.getNombre().equals(nombre)) {
              return new DataEvento(
                  e.getNombre(),
                  e.getDescripcionEvento(),
                  e.getSigla(),
                  e.getFecha(),
                  e.getCategoriasLista(),
                  e.getImagen()
              );
          }
      }
      throw new EventoNoExisteException("El evento con nombre \"" + nombre + "\" no existe.");
  }
      
     
  public DataEvento[] listarEventoExistentes() throws EventoNoExisteException {
      	 Map<String, Evento> eventos = manejadorEvento.getEventos();
  
      	 if (eventos != null) {
      		 DataEvento[] dataEvento = new DataEvento[eventos.size()];
      		 int indice = 0;
      		 for (Evento eve : eventos.values()) {
      			 dataEvento[indice++] = new DataEvento(eve.getNombre(), eve.getDescripcionEvento(), eve.getSigla(), eve.getFecha(), eve.getCategoriasLista(), eve.getImagen());
      		 }
      		Arrays.sort(dataEvento, Comparator.comparing(DataEvento::getNombre));
  
      		 return dataEvento;
      	 }else {
      		 throw new EventoNoExisteException("No existen eventos registrados");
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
        	        "La fecha de inicio de la edición a crear debe ser posterior a la fecha de alta en plataforma del evento \"" 
        	                + evento.getNombre() + "\""
        	            );
        if (nombreEdicionRepetido(nombreEdicion)) {
            throw new IllegalArgumentException("Ya existe una edición con ese nombre" + nombreEdicion);
        }
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        Organizador org = manejadorUsuario.getOrganizador(organizadorNick);
        if (org == null) {
            throw new IllegalArgumentException("Organizador inexistente: " + organizadorNick);
          }
          
          EdicionEvento edicion = new EdicionEvento( nombreEdicion, fechaInicio, fechaFin, ciudad, pais, sigla, fechaAltaEnPlataforma != null ? fechaAltaEnPlataforma : LocalDate.now());
          edicion.setOrganizador(org);
          edicion.setEventoPadre(evento);
          org.agregarEdicion(edicion);
          evento.agregarEdicion(edicion);
  }
      
  public void altaEdicionEvento(
              String nombreEvento, String nombreEdicion, String sigla, String ciudad, String pais, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAltaEnPlataforma, String organizadorNick, String imagen) throws UsuarioNoExisteException {
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
        	        "La fecha de inicio de la edición a crear debe ser posterior a la fecha de alta en plataforma del evento \"" 
        	                + evento.getNombre() + "\""
        	            );
        if (nombreEdicionRepetido(nombreEdicion)) {
            throw new IllegalArgumentException("Ya existe una edición con ese nombre" + nombreEdicion);
        }
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        Organizador org = manejadorUsuario.getOrganizador(organizadorNick);
        if (org == null) {
            throw new IllegalArgumentException("Organizador inexistente: " + organizadorNick);
          }
          
          EdicionEvento edicion = new EdicionEvento( nombreEdicion, fechaInicio, fechaFin, ciudad, pais, sigla, fechaAltaEnPlataforma != null ? fechaAltaEnPlataforma : LocalDate.now(), imagen);
          edicion.setOrganizador(org);
          edicion.setEventoPadre(evento);
          org.agregarEdicion(edicion);
          evento.agregarEdicion(edicion);
  }
  
  public void altaTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro, String descripcion, Integer costo,  Integer cupos) throws TipoRegistroRepetidoException {
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
            throw new TipoRegistroRepetidoException("Ya existe un tipo registro con nombre: " + nombreEdicion);
          }
          TipoRegistro tipoRegistronuevo = new TipoRegistro(nombreTipoRegistro, descripcion, costo, cupos);
          edicion.agregarTipoDeRegistro(nombreTipoRegistro, tipoRegistronuevo);
  }
      
  public void altaRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro, String nombreAsistente, LocalDate fecha) throws UsuarioNoExisteException {
          Evento evento = ManejadorEvento.getInstance().obtenerEvento(nombreEvento);
          if (evento == null) throw new IllegalArgumentException("No existe el evento: " + nombreEvento);
  
        EdicionEvento edicion = evento.getEdicion(nombreEdicion);
        if (edicion == null) throw new IllegalArgumentException("No existe la edición: " + nombreEdicion);
  
        TipoRegistro tipo = edicion.getTipoDeRegistro(nombreTipoRegistro);
        if (tipo == null) throw new IllegalArgumentException("No existe el tipo de registro: " + nombreTipoRegistro);
  
        Asistente asis = ManejadorUsuario.getInstance().getAsistente(nombreAsistente);
        if (asis == null) throw new IllegalArgumentException("No existe el asistente: " + nombreAsistente);
  
        if (!edicion.hayCupo(tipo)) {
            throw new IllegalStateException("No hay cupo disponible para el tipo de registro: " + nombreTipoRegistro);
        }
        if (edicion.estaRegistrado(asis)) {
            throw new IllegalStateException("El asistente ya está registrado en esta edición.");
        }
        LocalDate FechaAltaEdi = edicion.getFechaAltaEnPlataforma();
        if (fecha.isBefore(FechaAltaEdi))
        	throw new IllegalArgumentException("La fecha de Registro no puede ser anterior al de Alta de la edición.");
        LocalDate FechaFinEdi = edicion.getFechaFin();
        if (FechaFinEdi.isBefore(fecha))
        	throw new IllegalArgumentException("La fecha de Registro no puede ser poserior al fin de la edición.");
  
          
  
          int costo = tipo.getCosto();
          Registro reg = new Registro(fecha , costo, tipo, edicion, asis);
  
          edicion.agregarRegistro(reg);
          asis.agregarRegistro(reg);
  }
  
  public DataTipoRegistro[] listarTiposRegistro(String nombreEvento, String nombreEdicion) {
  		
      Evento evento = manejadorEvento.obtenerEvento(nombreEvento);
      if (evento == null) throw new IllegalArgumentException("No existe el evento: " + nombreEvento);
  
    EdicionEvento edicion = evento.getEdicion(nombreEdicion);
    if (edicion == null) throw new IllegalArgumentException("No existe la edición: " + nombreEdicion);
  
      DataTipoRegistro[] lista = edicion.getTiposRegistroDTO().toArray(new DataTipoRegistro[0]);
      Arrays.sort(lista, Comparator.comparing(DataTipoRegistro::getNombre));
      return lista;
  }
  
  public DataTipoRegistro getTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTipo) {
  		
    Evento evento = manejadorEvento.obtenerEvento(nombreEvento);
    if (evento == null) throw new IllegalArgumentException("No existe el evento: " + nombreEvento);
  
    EdicionEvento edicion = evento.getEdicion(nombreEdicion);
    if (edicion == null) throw new IllegalArgumentException("No existe la edición: " + nombreEdicion);
  
    TipoRegistro tipoReg = edicion.getTipoDeRegistro(nombreTipo);
    if (tipoReg == null) throw new IllegalArgumentException("No existe el tipo de registro: " + nombreTipo);
      return new DataTipoRegistro(
        tipoReg.getNombre(),
        tipoReg.getDescripcion(),
        tipoReg.getCosto(),
        tipoReg.getCupo()
      );
  	}
  	
	public void  setCostoRegistro(String nickname, String edicion, String nombreTipo, int precio) {
		
	    for (Evento e : manejadorEvento.getEventos().values()) {
	        for (EdicionEvento ed : e.getEdiciones().values()) {
	            for (Registro r : ed.getRegistros()) {
	                if ( edicion.equals(ed.getNombre())
	                		&& nickname.equals(r.getAsistente().getNickname()) 
	                		&& nombreTipo.equals(r.getTipoRegistro().toString() ) ) {
	                    r.setCosto(precio);
	                }
	            }
	        }
	    }
	}
  	
  public DataRegistro[] listarRegistrosDeEdicion(String nombreEdicion) {
    List<DataRegistro> out = new ArrayList<>();
    EdicionEvento edi = manejadorEvento.getEdicion(nombreEdicion);
    if (edi == null) return new DataRegistro[0];
    java.util.Collection<Registro> listaRegistros = edi.getRegistros();
    if (listaRegistros == null || listaRegistros.isEmpty()) return new DataRegistro[0];
    for (Registro r : listaRegistros) {
        out.add(new DataRegistro(edi.getEvento().getNombre(), edi.getNombre(), r.getTipoRegistro().getNombre(), r.getCostoRegistro(), r.getFechaRegistro(), r.getAsistente().getNickname()));
    }
    return out.toArray(new DataRegistro[0]);
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
  	
  public DataRegistro listarUnRegistroDeUsuario(String nombreEdicion, String nickname){
    DataRegistro out = null;
    for (Evento e : manejadorEvento.getEventos().values()) {
      for (EdicionEvento ed : e.getEdiciones().values()) {
        if (nombreEdicion.equals(ed.getNombre()) ){
          for (Registro r : ed.getRegistros()) {
            if (nickname.equals(r.getAsistente().getNickname())) {
                out = new DataRegistro(
                    e.getNombre(),
                    ed.getNombre(),
                    r.getTipoRegistro().getNombre(),
                    r.getCostoRegistro(),
                    r.getFechaRegistro(),
                    r.getAsistente().getNickname()
                );
            }
          }
        }
      }
    }
    return out;
  }
  
  
	public void limpiar() {
		manejadorEvento.limpiar();
	}
	
	public Boolean nombreEdicionRepetido(String nombreEdicion) {
		Collection<Evento> eventos = manejadorEvento.obtenerTodosEventos();
		for (Evento eve : eventos) {
			if (eve.getEdicion(nombreEdicion)!=null) {
				return true;
			}
		}
		return false;
	}
	
  public void aceptarEdicion(String nombreEdicion, Boolean aceptada)
          throws EdicionNoExisteException, TransicionEstadoInvalidaException {
  
      EdicionEvento edicion  = manejadorEvento.getEdicion(nombreEdicion);
      if (edicion == null) {
          throw new EdicionNoExisteException("No existe la edición: " + nombreEdicion);
    }
  
    if (edicion.getEstado() != EstadoEdicion.INGRESADA) {
        throw new TransicionEstadoInvalidaException(
            "Solo se puede cambiar el estado a una edición en estado INGRESADA"
          );
      }
  
      if (aceptada) {
          edicion.aceptar();
      } else {
          edicion.rechazar();
      }
  }  
  
  //listar edciones:
  public DataEdicion[] listarEdiciones(String nombreEvento) throws EventoNoExisteException {
  	Evento eve = manejadorEvento.obtenerEvento(nombreEvento);
  
  	Map<String, EdicionEvento> ediciones = eve.getEdiciones();
  	if (ediciones != null) {
  		DataEdicion[] dEdi = new DataEdicion[ediciones.size()];
  		int indice = 0;
  		for (EdicionEvento edi : ediciones.values()) {
  			dEdi[indice++] = new DataEdicion(
  				    edi.getNombre(),
  				    edi.getFechaIni(),
  				    edi.getFechaFin(),
  				    edi.getCiudad(),
  				    edi.getPais(),
  				    edi.getSigla(),
  				    edi.getFechaAltaEnPlataforma(),
  				    edi.getOrganizadorDTO(),   
  				    edi.getTiposRegistroDTO(),  
  				    edi.getPatrociniosDTO(),
  				    edi.getImagen(),
  				    edi.getEstado().name().toLowerCase(),
  				    edi.getEvento().getNombre()
  				);
  
  		}
  		Arrays.sort(dEdi, Comparator.comparing(DataEdicion::getNombre));
  		return dEdi;
  
  	}else {
  		throw new EventoNoExisteException("No existen ediciones registradas del Evento");
  	}
  }
  
  public DataEdicion[] listarEdicionesOrganizador(String nombreOrganizador) throws EdicionNoExisteException {
    Map<String, Evento> eventos = manejadorEvento.getEventos();
    List<DataEdicion> acumulado = new ArrayList<>();
    if (eventos != null && !eventos.isEmpty()) {
        for (Evento eve : eventos.values()) {
            Map<String, EdicionEvento> ediciones = eve.getEdiciones();
            if (ediciones == null || ediciones.isEmpty()) continue;
  
            for (EdicionEvento edi : ediciones.values()) {
                if (nombreOrganizador.equals(edi.getOrganizador().getNickname())) {
                    acumulado.add(new DataEdicion(
                        edi.getNombre(),
                        edi.getFechaIni(),
                        edi.getFechaFin(),
                        edi.getCiudad(),
                        edi.getPais(),
                        edi.getSigla(),
                        edi.getFechaAltaEnPlataforma(),
                        edi.getOrganizadorDTO(),
                        edi.getTiposRegistroDTO(),
                        edi.getPatrociniosDTO(),
                        edi.getImagen(),
                        edi.getEstadoString(),
                        edi.getEvento().getNombre()
                    ));
                }
            }
        }
    }
    if (acumulado.isEmpty()) {
        throw new EdicionNoExisteException("No existen ediciones");
    }
    acumulado.sort(Comparator.comparing(DataEdicion::getNombre));
    return acumulado.toArray(new DataEdicion[0]);
  }
  
  public DataEdicion[] listarEdicionesAceptadas() throws EdicionNoExisteException {
    Map<String, Evento> eventos = manejadorEvento.getEventos();
    List<DataEdicion> acumulado = new ArrayList<>();
    if (eventos != null && !eventos.isEmpty()) {
        for (Evento eve : eventos.values()) {
            Map<String, EdicionEvento> ediciones = eve.getEdiciones();
            if (ediciones == null || ediciones.isEmpty()) continue;
  
            for (EdicionEvento edi : ediciones.values()) {
                if (edi.getEstado() == EstadoEdicion.ACEPTADA) {
                    acumulado.add(new DataEdicion(
                        edi.getNombre(),
                        edi.getFechaIni(),
                        edi.getFechaFin(),
                        edi.getCiudad(),
                        edi.getPais(),
                        edi.getSigla(),
                        edi.getFechaAltaEnPlataforma(),
                        edi.getOrganizadorDTO(),
                        edi.getTiposRegistroDTO(),
                        edi.getPatrociniosDTO(),
                        edi.getImagen(),
                        edi.getEstadoString(),
                        edi.getEvento().getNombre()
                    ));
                }
            }
        }
    }
    if (acumulado.isEmpty()) {
        throw new EdicionNoExisteException("No existen ediciones con estado ACEPTADA");
    }
    acumulado.sort(Comparator.comparing(DataEdicion::getNombre));
    return acumulado.toArray(new DataEdicion[0]);
  }
  
  public DataEdicion[] listarEdicionesAceptadasEvento(String nombreEvento) throws EdicionNoExisteException , EventoNoExisteException {
    Evento eve = manejadorEvento.obtenerEvento(nombreEvento);
    if (eve == null) {
        throw new EventoNoExisteException("El evento \"" + nombreEvento + "\" no existe");
  }
  Map<String, EdicionEvento> ediciones = eve.getEdiciones();
  if (ediciones == null || ediciones.isEmpty()) {
      throw new EdicionNoExisteException("El evento \"" + nombreEvento + "\" no tiene ediciones");
  }
  List<DataEdicion> acumulado = new ArrayList<>();
  for (EdicionEvento edi : ediciones.values()) {
      if (edi.getEstado() == EstadoEdicion.ACEPTADA) {
          acumulado.add(new DataEdicion(
              edi.getNombre(),
              edi.getFechaIni(),
              edi.getFechaFin(),
              edi.getCiudad(),
              edi.getPais(),
              edi.getSigla(),
              edi.getFechaAltaEnPlataforma(),
              edi.getOrganizadorDTO(),
              edi.getTiposRegistroDTO(),
              edi.getPatrociniosDTO(),
              edi.getImagen(),
              edi.getEstadoString(),
              edi.getEvento().getNombre()
          ));
      }
  }
  if (acumulado.isEmpty()) {
      throw new EdicionNoExisteException(
          "No existen ediciones ACEPTADA para el evento \"" + nombreEvento + "\"");
    }
    acumulado.sort(Comparator.comparing(DataEdicion::getNombre, String.CASE_INSENSITIVE_ORDER));
    return acumulado.toArray(new DataEdicion[0]);
  }
  
  public DataEdicion[] listarEdicionesOrganizadorAceptadas(String nombreOrganizador) throws EdicionNoExisteException{
    Map<String, Evento> eventos = manejadorEvento.getEventos();
    List<DataEdicion> acumulado = new ArrayList<>();
    if (eventos != null && !eventos.isEmpty()) {
        for (Evento eve : eventos.values()) {
            Map<String, EdicionEvento> ediciones = eve.getEdiciones();
            if (ediciones == null || ediciones.isEmpty()) continue;
  
            for (EdicionEvento edi : ediciones.values()) {
                if (nombreOrganizador.equals(edi.getOrganizador().getNickname()) && edi.getEstado() == EstadoEdicion.ACEPTADA) {
                    acumulado.add(new DataEdicion(
                        edi.getNombre(),
                        edi.getFechaIni(),
                        edi.getFechaFin(),
                        edi.getCiudad(),
                        edi.getPais(),
                        edi.getSigla(),
                        edi.getFechaAltaEnPlataforma(),
                        edi.getOrganizadorDTO(),
                        edi.getTiposRegistroDTO(),
                        edi.getPatrociniosDTO(),
                        edi.getImagen(),
                        edi.getEstadoString(),
                        edi.getEvento().getNombre()
                    ));
                }
            }
        }
    }
    if (acumulado.isEmpty()) {
        throw new EdicionNoExisteException("No existen ediciones con estado ACEPTADA");
    }
    acumulado.sort(Comparator.comparing(DataEdicion::getNombre));
    return acumulado.toArray(new DataEdicion[0]);
  }
  
  
  
  public DataEdicion[] listarEdicionesIngresadas() throws EdicionNoExisteException {
    Map<String, Evento> eventos = manejadorEvento.getEventos();
    List<DataEdicion> acumulado = new ArrayList<>();
    if (eventos != null && !eventos.isEmpty()) {
        for (Evento eve : eventos.values()) {
            Map<String, EdicionEvento> ediciones = eve.getEdiciones();
            if (ediciones == null || ediciones.isEmpty()) continue;
  
            for (EdicionEvento edi : ediciones.values()) {
                if (edi.getEstado()== EstadoEdicion.INGRESADA) {
                  acumulado.add(new DataEdicion(
                      edi.getNombre(),
                      edi.getFechaIni(),
                      edi.getFechaFin(),
                      edi.getCiudad(),
                      edi.getPais(),
                      edi.getSigla(),
                      edi.getFechaAltaEnPlataforma(),
                      edi.getOrganizadorDTO(),
                      edi.getTiposRegistroDTO(),
                      edi.getPatrociniosDTO(),
                      edi.getImagen(),
                      edi.getEstadoString(),
                      edi.getEvento().getNombre()
                  ));
              }
          }
      }
  }
  if (acumulado.isEmpty()) {
      throw new EdicionNoExisteException("No existen ediciones con estado INGRESADA");
    }
    acumulado.sort(Comparator.comparing(DataEdicion::getNombre));
    return acumulado.toArray(new DataEdicion[0]);
  }
    
  public DataEdicion[] listarEdicionesIngresadasEvento(String nombreEvento) throws EdicionNoExisteException{
    Map<String, Evento> eventos = manejadorEvento.getEventos();
    List<DataEdicion> acumulado = new ArrayList<>();
    if (eventos != null && !eventos.isEmpty()) {
        for (Evento eve : eventos.values()) {
            if (nombreEvento.equals(eve.getNombre())) {
                Map<String, EdicionEvento> ediciones = eve.getEdiciones();
                if (ediciones == null || ediciones.isEmpty()) continue;
  
                for (EdicionEvento edi : ediciones.values()) {
                    if (edi.getEstado() == EstadoEdicion.INGRESADA) {
                        acumulado.add(new DataEdicion(
                            edi.getNombre(),
                            edi.getFechaIni(),
                            edi.getFechaFin(),
                            edi.getCiudad(),
                            edi.getPais(),
                            edi.getSigla(),
                            edi.getFechaAltaEnPlataforma(),
                            edi.getOrganizadorDTO(),
                            edi.getTiposRegistroDTO(),
                            edi.getPatrociniosDTO(),
                            edi.getImagen(),
                            edi.getEstadoString(),
                            edi.getEvento().getNombre()
                        ));
                    }
                }
            }
        }
    }
    if (acumulado.isEmpty()) {
        throw new EdicionNoExisteException("No existen ediciones con estado INGRESADA");
    }
    acumulado.sort(Comparator.comparing(DataEdicion::getNombre));
    return acumulado.toArray(new DataEdicion[0]);
  }
  
  public DataEdicion[] listarEdicionesRechazadas() throws EdicionNoExisteException {
    Map<String, Evento> eventos = manejadorEvento.getEventos();
    List<DataEdicion> acumulado = new ArrayList<>();
    if (eventos != null && !eventos.isEmpty()) {
        for (Evento eve : eventos.values()) {
            Map<String, EdicionEvento> ediciones = eve.getEdiciones();
            if (ediciones == null || ediciones.isEmpty()) continue;
  
            for (EdicionEvento edi : ediciones.values()) {
                if (edi.getEstado() == EstadoEdicion.RECHAZADA) {
                    acumulado.add(new DataEdicion(
                        edi.getNombre(),
                        edi.getFechaIni(),
                        edi.getFechaFin(),
                        edi.getCiudad(),
                        edi.getPais(),
                        edi.getSigla(),
                        edi.getFechaAltaEnPlataforma(),
                        edi.getOrganizadorDTO(),
                        edi.getTiposRegistroDTO(),
                        edi.getPatrociniosDTO(),
                        edi.getImagen(),
                        edi.getEstadoString(),
                        edi.getEvento().getNombre()
                    ));
                }
            }
        }
    }
    if (acumulado.isEmpty()) {
        throw new EdicionNoExisteException("No existen ediciones con estado RECHAZADA");
    }
    acumulado.sort(Comparator.comparing(DataEdicion::getNombre));
    return acumulado.toArray(new DataEdicion[0]);
  }
  
  public DataEdicion[] listarEdicionesRechazadasEvento(String nombreEvento) throws EdicionNoExisteException{
    Map<String, Evento> eventos = manejadorEvento.getEventos();
    List<DataEdicion> acumulado = new ArrayList<>();
    if (eventos != null && !eventos.isEmpty()) {
        for (Evento eve : eventos.values()) {
            if (nombreEvento.equals(eve.getNombre())) {
                Map<String, EdicionEvento> ediciones = eve.getEdiciones();
                if (ediciones == null || ediciones.isEmpty()) continue;
  
                for (EdicionEvento edi : ediciones.values()) {
                    if (edi.getEstado() == EstadoEdicion.RECHAZADA) {
                        acumulado.add(new DataEdicion(
                            edi.getNombre(),
                            edi.getFechaIni(),
                            edi.getFechaFin(),
                            edi.getCiudad(),
                            edi.getPais(),
                            edi.getSigla(),
                            edi.getFechaAltaEnPlataforma(),
                            edi.getOrganizadorDTO(),
                            edi.getTiposRegistroDTO(),
                            edi.getPatrociniosDTO(),
                            edi.getImagen(),
                            edi.getEstadoString(),
                            edi.getEvento().getNombre()
                        ));
                    }
                }
            }
        }
    }
    if (acumulado.isEmpty()) {
        throw new EdicionNoExisteException("No existen ediciones con estado RECHAZADA");
    }
    acumulado.sort(Comparator.comparing(DataEdicion::getNombre));
    return acumulado.toArray(new DataEdicion[0]);
  }

  public List<String> listarCategorias(){
    return  manejadorEvento.getNombresCategorias();
  }
  
  public String getEventoDeUnaEdicion(String nombreEdicion){
    return  manejadorEvento.getEdicion(nombreEdicion).getEvento().getNombre();
  }
  
}
  
