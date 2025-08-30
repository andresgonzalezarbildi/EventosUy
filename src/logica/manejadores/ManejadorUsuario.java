package logica.manejadores;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import logica.clases.Asistente;
import logica.clases.Organizador;
import logica.clases.Usuario;

public class ManejadorUsuario {

    private static ManejadorUsuario instancia;

    private final Map<String, Organizador> organizadores;
    private final Map<String, Asistente>  asistentes;

    private ManejadorUsuario() {
        this.organizadores = new HashMap<>();
        this.asistentes    = new HashMap<>();
    }

    public static ManejadorUsuario getInstance() {
        if (instancia == null) {
            instancia = new ManejadorUsuario();
        }
        return instancia;
    }

    public boolean existeNickname(String nickname) {
        return organizadores.containsKey(nickname) || asistentes.containsKey(nickname);
    }
    
    //chequeo los correos
    public boolean existeCorreo(String correo) {
        // Recorrer organizadores
        for (Organizador org : organizadores.values()) {
            if (org.getCorreo().equalsIgnoreCase(correo)) {
                return true;
            }
        }

        // Recorrer asistentes
        for (Asistente asis : asistentes.values()) {
            if (asis.getCorreo().equalsIgnoreCase(correo)) {
                return true;
            }
        }

        return false;
    }

    public void agregarOrganizador(Organizador org) {
        organizadores.put(org.getNickname(), org);
    }

    public void agregarAsistente(Asistente asis) {
        asistentes.put(asis.getNickname(), asis);
    }

    public Usuario obtenerPorNickname(String nickname) {
        Usuario u = organizadores.get(nickname);
        if (u != null) return u;
        return asistentes.get(nickname);
    }
    
    public Asistente getAsistente(String nickname) {
        return asistentes.get(nickname);
        
    }
    
    public Organizador obtenerOrganizadorPorNickname(String nickname) {
        return organizadores.get(nickname);

    }

    public Collection<Organizador> obtenerTodosOrganizadores() {
        return organizadores.values();
    }

    public Collection<Asistente> obtenerTodosAsistentes() {
        return asistentes.values();
    }
    
}