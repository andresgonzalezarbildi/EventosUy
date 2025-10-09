package logica.clases;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Map;
import java.util.List;
public class Evento {	
	private String nombre;
    private String DescripcionEvento;
    private String sigla;
    private LocalDate FechaAltaEnPlataforma; 
    private Map<String, Categoria> categoriasDeEvento;
    private Map<String, EdicionEvento> edicionesDeEvento;
    private String imagen;
    
    public Evento(String nom, String desc, String sigla, LocalDate fecha, String imagen) {
        this.DescripcionEvento = desc;
        this.nombre = nom;
        this.sigla = sigla;
        this.FechaAltaEnPlataforma = fecha;
        this.categoriasDeEvento = new HashMap<>();
        this.edicionesDeEvento = new HashMap<>();
        this.imagen = (imagen == null || imagen.isBlank()) ? "EventoSinFoto.png" : imagen;
    }
    
/*    public Evento(String nom, String desc, String sigla, LocalDate fecha) {
        this.DescripcionEvento = desc;
        this.nombre = nom;
        this.sigla = sigla;
        this.FechaAltaEnPlataforma = fecha;
        this.categoriasDeEvento = new HashMap<>();
        this.edicionesDeEvento = new HashMap<>();
        setImagen(null);
    }
*/    
    public LocalDate getFecha() {
        return FechaAltaEnPlataforma;
    }
    public String getSigla() {
        return sigla;
    }
    public String getDescripcionEvento() {
        return DescripcionEvento;
    }
    public void setFecha(LocalDate fecha) {
        this.FechaAltaEnPlataforma = fecha;
    }
    public String getNombre() {
        return nombre;
    }
    public Map<String, Categoria> getCategorias() { 
    		return categoriasDeEvento; }
    
    public void agregarCategoria(Categoria c) {
        categoriasDeEvento.put(c.getNombre(), c);
    }

    
    //getCategoriasLista convierte el Map en una List
    public List<String> getCategoriasLista() { 				//categoriasDeEvento.keySet() devuelve un Set<String> con 
        return new ArrayList<>(categoriasDeEvento.keySet());	//todas las claves (los nombres de las categorías).
    }														//new ArrayList<>(...) transforma ese Set en una List<String>
   
  
    public Map<String, EdicionEvento> getEdiciones() { 
    	return edicionesDeEvento;
    }
    
    public EdicionEvento getEdicion(String nombreEdicion) {
    	return edicionesDeEvento.get(nombreEdicion);
    }
    
    public void agregarEdicion(EdicionEvento e) {
    	edicionesDeEvento.put(e.getNombre(), e);
    }
    
    public String getImagen() {
        return imagen;
    }

   public void setImagen(String imagen) {
       this.imagen = (imagen == null || imagen.isBlank()) ? "EventoSinFoto.png" : imagen;
   }
}
    

    
    