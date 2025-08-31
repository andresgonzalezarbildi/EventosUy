package logica.manejadores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.clases.Categoria;
import logica.clases.EdicionEvento;
import logica.clases.Evento;
import logica.datatypes.DataEvento;

public class ManejadorEvento {

    private static ManejadorEvento instancia;

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



    public void agregarEvento(Evento e) {
        eventos.put(e.getNombre(), e);
    }

    public Evento obtenerEvento(String nombre) {
        return eventos.get(nombre);
    }

    public void eliminarEvento(String nombre) {
        eventos.remove(nombre);
    }

    public Map<String, Evento> getEventos() {
        return eventos;
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
    
    public boolean existeEvento(String nombre) {
        return eventos.containsKey(nombre);
    }

 

    
    public void agregarCategoria(Categoria c) {
        categorias.put(c.getNombre(), c);
    }
    
    public Categoria obtenerCategoria(String nombre) {
        return categorias.get(nombre);
    }
    
    public List<Categoria> getCategorias() {
        return new ArrayList<>(categorias.values());
    }

    public List<String> getNombresCategorias() {
        return new ArrayList<>(categorias.keySet());
    }

    public Categoria[] getCategoriasArray() {
        return categorias.values().toArray(new Categoria[0]);
    }
    
    public boolean existeCategoria(String nombre) {
        return categorias.containsKey(nombre);
    }
    
    public EdicionEvento getEdicion(String idEdicion) {
        for (Evento ev : eventos.values()) {
            EdicionEvento ed = ev.getEdicion(idEdicion);
            if (ed != null) {
                return ed;
            }
        }
        return null; // si no se encuentra
    }
    



    public boolean existeEdicion(String nombreEdicion) {
        if (nombreEdicion == null) return false;
        for (Evento e : eventos.values()) {
            if (e.getEdiciones().containsKey(nombreEdicion)) return true;
        }
        return false;
    }
    
}
