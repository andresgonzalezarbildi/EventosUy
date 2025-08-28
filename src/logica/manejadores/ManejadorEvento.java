package logica.manejadores;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import logica.clases.Categoria;
import logica.clases.EdicionEvento;
import logica.clases.Evento;

public class ManejadorEvento {
    private static ManejadorEvento instancia = null;
    private Map<String, Evento> eventos;
    private Map<String, Categoria> categorias;
    private ManejadorEvento() {
        eventos = new HashMap<>();
    }	
    public static ManejadorEvento getInstance() {
        if (instancia == null) {
            instancia = new ManejadorEvento();
        }
        return instancia;
    }
    public void agregarEvento(Evento e) {
        eventos.put(e.getNombre(), e);
    }  
    public Evento obtenerEvento(String nom) {
        return eventos.get(nom);
    }
    public void eliminarEvento(String nom) {
        eventos.remove(nom);
    }
    public Map<String, Evento> getEventos() {
        return eventos;
    }
    
    public void agregarCategoria(Categoria c) {
        categorias.put(c.getNombre(), c);
    }
    public DataEvento[] getEventosDTO() {
        List<DataEvento> lista = new ArrayList<>();
        for (Evento e : eventos.values()) {
            lista.add(new DataEvento(
                e.getNombre(),
                e.getDescripcionEvento(),
                e.getSigla(),
                e.getFecha(),
                e.getCategoriasLista()
            ));
        }
        return lista.toArray(new DataEvento[0]);
    }
    
 }
