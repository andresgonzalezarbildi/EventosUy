package logica;

import java.util.HashMap;
import java.util.Map;

public class ManejadorEvento {

    private static ManejadorEvento instancia = null;

    private Map<String, Evento> eventos;

    private ManejadorEvento() {
        eventos = new HashMap<>();
    }

    public static ManejadorEvento getInstance() {
        if (instancia == null) {
            instancia = new ManejadorEvento();
        }
        return instancia;
    }

    // Agregar evento
    public void agregarEvento(Evento e) {
        eventos.put(e.getNombre(), e);
    }

    // Obtener evento por sigla
    public Evento obtenerEvento(String nom) {
        return eventos.get(nom);
    }

    // Eliminar evento
    public void eliminarEvento(String nom) {
        eventos.remove(nom);
    }

    // Listar todos los eventos
    public Map<String, Evento> getEventos() {
        return eventos;
    }
}
