package logica.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evento {	
  private String nombre;
  private String descripcionEvento;
  private String sigla;
  private LocalDate fechaAltaEnPlataforma; 
  private Map<String, Categoria> categoriasDeEvento;
  private Map<String, EdicionEvento> edicionesDeEvento;
  private String imagen;
  private boolean finalizado;
    
  public Evento(String nom, String desc, String sigla, LocalDate fecha, String imagen) {
    this.descripcionEvento = desc;
    this.nombre = nom;
    this.sigla = sigla;
    this.fechaAltaEnPlataforma = fecha;
    this.categoriasDeEvento = new HashMap<>();
    this.edicionesDeEvento = new HashMap<>();
    this.imagen = (imagen == null || imagen.isBlank()) ? "EventoSinFoto.png" : imagen;
    this.finalizado = false;
  }
        
  public LocalDate getFecha() {
    return fechaAltaEnPlataforma;
  }
  
  public String getSigla() {
    return sigla;
  }
  
  public String getDescripcionEvento() {
    return descripcionEvento;
  }
  
  public void setFecha(LocalDate fecha) {
    this.fechaAltaEnPlataforma = fecha;
  }
  
  public String getNombre() {
    return nombre;
  }
  
  public Map<String, Categoria> getCategorias() { 
    return categoriasDeEvento; 
  }
  
  public void agregarCategoria(Categoria categoria) {
    categoriasDeEvento.put(categoria.getNombre(), categoria);
  }

    
  //getCategoriasLista convierte el Map en una List
  public List<String> getCategoriasLista() {
    return new ArrayList<>(categoriasDeEvento.keySet());	
  }
 
  
  public Map<String, EdicionEvento> getEdiciones() { 
    return edicionesDeEvento;
  }
  
  public EdicionEvento getEdicion(String nombreEdicion) {
    return edicionesDeEvento.get(nombreEdicion);
  }
  
  public void agregarEdicion(EdicionEvento edicionEvento) {
    edicionesDeEvento.put(edicionEvento.getNombre(), edicionEvento);
  }
  
  public String getImagen() {
    return imagen;
  }

  public void setImagen(String imagen) {
    this.imagen = (imagen == null || imagen.isBlank()) ? "EventoSinFoto.png" : imagen;
  }
  
  public boolean getFinalizado() {
		return finalizado;
  }
  
  public void setFinalizado(boolean estado) {
	this.finalizado = estado;
  }
}
    

    
    