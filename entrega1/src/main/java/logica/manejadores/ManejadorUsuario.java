package logica.manejadores;

import excepciones.UsuarioNoExisteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import logica.clases.Asistente;
import logica.clases.Organizador;
import logica.clases.Usuario;

/**
 * Manejador encargado de administrar los usuarios del sistema.
 * Implementa el patrón Singleton.
 */
public class ManejadorUsuario {

  private static ManejadorUsuario instancia;
  
  private final Map<String, Organizador> organizadores;
  private final Map<String, Asistente>  asistentes;
  
  /**
   * Constructor.
   * Inicializa las colecciones de organizadores y asistentes.
   */
  private ManejadorUsuario() {
    this.organizadores = new HashMap<>(); 
    this.asistentes    = new HashMap<>();
  }
  
  /**
   * Devuelve la instancia única del manejador de usuarios.
   *
   * @return instancia única de ManejadorUsuario
   */
  public static ManejadorUsuario getInstance() {
    if (instancia == null) {
      instancia = new ManejadorUsuario();
    }
    return instancia;
  }
  
  /**
   * Verifica si existe un usuario con el nickname dado.
   *
   * @param nickname nombre de usuario a verificar
   * @return true si el nickname ya existe, false en caso contrario
   */
  public boolean existeNickname(String nickname) {
    return organizadores.containsKey(nickname) || asistentes.containsKey(nickname);
  }
  
  /**
   * Verifica si existe un usuario con el correo dado.
   *
   * @param correo correo electrónico a verificar
   * @return true si el correo ya existe, false en caso contrario
   */
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
    Usuario usuario = organizadores.get(nickname);
    if (usuario != null) {
      return usuario;
    }
    return asistentes.get(nickname);
  }
  
  public Usuario obtenerPorCorreo(String correo) {
    if (correo == null || correo.isBlank()) return null;
    for (Organizador org : organizadores.values()) {
        if (correo.equalsIgnoreCase(org.getCorreo())) {
            return org;
        }
    }
    for (Asistente asis : asistentes.values()) {
        if (correo.equalsIgnoreCase(asis.getCorreo())) {
            return asis;
        }
    }
    return null;
 }
  
  public Usuario obtenerAsistentePorNickname(String nickname) {
	    return asistentes.get(nickname);
  }
	  
  public Usuario obtenerAsistentePorCorreo(String correo) {
	    if (correo == null || correo.isBlank()) return null;
	    for (Asistente asis : asistentes.values()) {
	        if (correo.equalsIgnoreCase(asis.getCorreo())) {
	            return asis;
	        }
	    }
	    return null;
  }
  
  public Usuario obtenerAsistentePorIdentificador(String ident) { 
	    Usuario res = obtenerAsistentePorCorreo(ident);
	    if (res == null) {
	      res = obtenerAsistentePorNickname(ident);
	    }
	    return res;
  }
  
  public Usuario obtenerPorIdentificador(String ident) { 
    Usuario res = obtenerPorCorreo(ident);
    if (res == null) {
      res = obtenerPorNickname(ident);
    }
    return res;
  }
  
  
  public Organizador getOrganizador(String nickname) throws UsuarioNoExisteException {
    if (nickname == null || nickname.isBlank()) {
      throw new UsuarioNoExisteException("Nickname inválido (null o vacío)");
    }
    Organizador org = organizadores.get(nickname);
    if (org == null) {
      throw new UsuarioNoExisteException("No existe organizador con nickname: " + nickname);
    }
    return org;
  }

  public Asistente getAsistente(String nickname) throws UsuarioNoExisteException {
    Asistente asis = asistentes.get(nickname);
    if (asis == null) {
      throw new UsuarioNoExisteException("No existe asistente con nickname: " + nickname);
    }
    return asis;
  }
  
  
  public Collection<Organizador> obtenerTodosOrganizadores() {
    return organizadores.values();
  }
  
  public Collection<Asistente> obtenerTodosAsistentes() {
    return asistentes.values();
  }
  
  public void limpiar() {
    organizadores.clear();
    asistentes.clear();
  }
  
  public boolean existeNombre(String nombre) {
    for (Organizador org : organizadores.values()) {
      if (org.getNombre().equalsIgnoreCase(nombre)) {
        return true;
      }
    }
    for (Asistente asis : asistentes.values()) {
      if (asis.getNombre().equalsIgnoreCase(nombre)) {
        return true;
      }
    }
    return false;
  }

    
}