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
    
    public Evento(String nom, String desc, String sigla) {
        this.DescripcionEvento = desc;
        this.nombre = nom;
        this.sigla = sigla;
        this.FechaAltaEnPlataforma = LocalDate.now();;
        this.categoriasDeEvento = new HashMap<>();
        this.edicionesDeEvento = new HashMap<>();
    }
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
    
    public void agregarEdicion(EdicionEvento e) {
    	edicionesDeEvento.put(e.getNombre(), e);
    }
}


    

    
    