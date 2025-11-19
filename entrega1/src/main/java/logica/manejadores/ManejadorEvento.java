package logica.manejadores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logica.clases.Categoria;
import logica.clases.EdicionEvento;
import logica.clases.Evento;
import logica.datatypes.DataEvento;

/**
 * Manejador encargado de administrar los eventos, ediciones y categorías del sistema.
 * Implementa el patrón Singleton.
 */
public class ManejadorEvento {

  private static ManejadorEvento instancia;
  
  /**
   * Devuelve la instancia única del manejador de eventos.
   *
   * @return instancia única de ManejadorEvento
   */
  public static ManejadorEvento getInstance() {
    if (instancia == null) {
      instancia = new ManejadorEvento();
    }
    return instancia;
  }
  
  private final Map<String, Evento> eventos;
  private final Map<String, Categoria> categorias;
  
  private ManejadorEvento() {
    this.eventos = new HashMap<>();
    this.categorias = new HashMap<>();
  }
  

  /**
   * Agrega un nuevo evento al sistema.
   *
   * @param e evento a agregar
   */
  public void agregarEvento(Evento evento) {
    eventos.put(evento.getNombre(), evento);
  }
  
  /**
   * Obtiene un evento por su nombre.
   *
   * @param nombre nombre del evento
   * @return evento correspondiente o null si no existe
   */
  public Evento obtenerEvento(String nombre) {
    return eventos.get(nombre);
  }
  
  /**
   * Elimina un evento del sistema.
   *
   * @param nombre nombre del evento a eliminar
   */
  public void eliminarEvento(String nombre) {
    eventos.remove(nombre);
  }
  
  public Map<String, Evento> getEventos() {
    return eventos;
  }
  
  /**
   * Obtiene todos los eventos registrados.
   *
   * @return colección de eventos
   */
  public Collection<Evento> obtenerTodosEventos() {
    return eventos.values();
  }
  
  /**
   * Devuelve los datos de todos los eventos en formato DataEvento.
   *
   * @return arreglo con la información de los eventos
   */
  public DataEvento[] getEventosDataType() {
    List<DataEvento> lista = new ArrayList<>();
    for (Evento e : eventos.values()) {
      lista.add(new DataEvento(
          e.getNombre(),
          e.getDescripcionEvento(),
          e.getSigla(),
          e.getFecha(),
          e.getCategoriasLista(),
          e.getImagen(),
          e.getFinalizado()
      ));
    }
    lista.sort(Comparator.comparing(DataEvento::getNombre));
    return lista.toArray(new DataEvento[0]);
  }
   
  /**
   * Verifica si existe un evento con el nombre dado.
   *
   * @param nombre nombre del evento
   * @return true si existe, false en caso contrario
   */
  public boolean existeEvento(String nombre) {
    return eventos.containsKey(nombre);
  }
  
  /**
   * Agrega una nueva categoría al sistema.
   *
   * @param categoria categoría a agregar
   */
  public void agregarCategoria(Categoria categoria) {
    categorias.put(categoria.getNombre(), categoria);
  }
      
  /**
   * Obtiene una categoría por su nombre.
   *
   * @param nombre nombre de la categoría
   * @return categoría correspondiente o null si no existe
   */
  public Categoria obtenerCategoria(String nombre) {
    return categorias.get(nombre);
  }
  
  /**
   * Devuelve la lista de categorías ordenadas por nombre.
   *
   * @return lista de categorías
   */
  public List<Categoria> getCategorias() {
    List<Categoria> lista = new ArrayList<>(categorias.values());
    lista.sort(Comparator.comparing(Categoria::getNombre)); // ordenamos antes de devolver
    return lista;
  
  }
  
  /**
   * Devuelve la lista de nombres de las categorías ordenadas.
   *
   * @return lista de nombres de categorías
   */
  public List<String> getNombresCategorias() {
    List<String> nombres = new ArrayList<>(categorias.keySet());
    Collections.sort(nombres); // ordenamos antes de devolver
    return nombres;
  }
  
  public Categoria[] getCategoriasArray() {
    return categorias.values().toArray(new Categoria[0]);
  }
  
  /**
   * Verifica si existe una categoría con el nombre dado.
   *
   * @param nombre nombre de la categoría
   * @return true si existe, false en caso contrario
   */
  public boolean existeCategoria(String nombre) {
    return categorias.containsKey(nombre);
  }
  

  /**
   * Busca una edición de evento por su identificador.
   *
   * @param idEdicion identificador de la edición
   * @return edición encontrada o null si no existe
   */
  public EdicionEvento getEdicion(String idEdicion) {
    for (Evento evento : eventos.values()) {
      EdicionEvento edicionEvento = evento.getEdicion(idEdicion);
      if (edicionEvento != null) {
        return edicionEvento;
      }
    }
    return null; // si no se encuentra
  }
  
  
  /**
   * Verifica si existe una edición con el nombre dado.
   *
   * @param nombreEdicion nombre de la edición
   * @return true si existe, false en caso contrario
   */
  public boolean existeEdicion(String nombreEdicion) {
    if (nombreEdicion == null) {
      return false;
    }
    for (Evento e : eventos.values()) {
      if (e.getEdiciones().containsKey(nombreEdicion)) { 
        return true;
      }
    }
    return false;
  }
  
  /**
   * Elimina todos los eventos y categorías registrados.
   */
  public void limpiar() {
    eventos.clear();
    categorias.clear();
  }
   
    
}
