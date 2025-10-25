package logica.controladores;

import excepciones.PasswordIncorrectaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import logica.clases.Asistente;
import logica.clases.Organizador;
import logica.clases.Usuario;
import logica.datatypes.DataUsuario;
import logica.interfaces.IControladorUsuario;
import logica.manejadores.ManejadorUsuario;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los usuarios.
 */
public class ControladorUsuario implements IControladorUsuario {
  private static ControladorUsuario instancia;
  private final ManejadorUsuario manejador;

  /**
   * Retorna la instancia única del controlador de usuario.
   * Crea una nueva si aún no existe.
   *
   * @return instancia única del controlador
   */
  public static ControladorUsuario getInstance() {
    if (instancia == null) {
      instancia = new ControladorUsuario();
    }
    return instancia;
  }
  
  /**
   * Constructor por defecto del controlador de usuario.
   * Inicializa la referencia al manejador de usuarios.
   */
  public ControladorUsuario() {
    this.manejador = ManejadorUsuario.getInstance();
  }

  /**
   * Funcion para dar de alta un Usuario en el Sistema.
   *
   * @param nickname nickname del usuario, el cual es unico en el sistema
   * @param nombre nombre del usuario
   * @param correo correo del usuario, unico en el sistema
   * @param imagen nombre de la imagen del usuario
   * @param password contrasenia del usuario
   * @param tipo tipo de usuario, organizador o asistente
   * @param descripcion  descripcion del organizador
   * @param link link de la pagina del organizador
   * @param apellido apellido del asistente
   * @param fechaNac fecha de nacimiento del asistente
   */
  public void altaUsuario(String nickname, String nombre, String correo, String imagen, 
                          String password, String tipo, String descripcion, String link,
                          String apellido, LocalDate fechaNac) throws UsuarioRepetidoException {
    String imagenFinal = "PerfilSinFoto.jpg";

    if (nickname == null || nickname.isBlank()) {
      throw new IllegalArgumentException("El nickname no puede ser vacío.");
    }
    if (nombre == null || nombre.isBlank()) {
      throw new IllegalArgumentException("El nombre no puede ser vacío.");
    }
    if (correo == null || correo.isBlank()) {
      throw new IllegalArgumentException("El correo no puede ser vacío.");
    }
    if (manejador.existeNickname(nickname)) {
      throw new UsuarioRepetidoException("El usuario " + nickname + " ya existe");
    }
    if (manejador.existeCorreo(correo)) {
      throw new UsuarioRepetidoException("El correo: " + correo + " ya pertenece a un usuario");
    }
    if (imagen != null) {
      imagenFinal = imagen;
    }       
    if ("Organizador".equalsIgnoreCase(tipo)) {
      Organizador org = new Organizador(nickname, nombre, correo, imagenFinal, password,
                                          descripcion, link);
      manejador.agregarOrganizador(org);
    
    } else if ("Asistente".equalsIgnoreCase(tipo)) {
      Asistente asis = new Asistente(nickname, nombre, correo, imagenFinal, password,
                                       apellido, fechaNac);
      manejador.agregarAsistente(asis);
    
    } else {
      throw new IllegalArgumentException("Tipo de usuario inválido: " + tipo);
    }
  }
  
  /**
   * Devuelve un DataUsuario con la información del usuario que tiene el nickname dado.
   *
   * @param nickname nombre de usuario a buscar
   * @return datos del usuario encontrado
   * @throws UsuarioNoExisteException si no existe un usuario con el nickname indicado
   */
  public DataUsuario verInfoUsuario(String nickname) throws UsuarioNoExisteException {
    Usuario usuarioBuscado = manejador.obtenerPorNickname(nickname);
    if (usuarioBuscado == null) {
      throw new UsuarioNoExisteException("Usuario no encontrado: " + nickname);
    }
    return convertirDataUsuario(usuarioBuscado);
  }
  
  /**
   * Modifica un usuario, identificado por su nickname.
   *
   * @param nickname del usuario
   * @param nombre nuevo del usuario
   * @param descripcion nueva del organizador
   * @param imagen nueva del usuario
   * @param link nuevo de la pagina del organizador
   * @param apellido nuevo del asistente
   * @param fechaNac nueva fecha de nacimiento del asistente 
   * @throws UsuarioNoExisteException si no existe un usuario con el nickname indicado
   */
  public void modificarUsuario(String nickname, String nombre, String descripcion, String imagen,
                               String link, String apellido, LocalDate fechaNac)
                               throws UsuarioNoExisteException {
    Usuario usuarioModificar = manejador.obtenerPorNickname(nickname);
    if (usuarioModificar == null) {
      throw new UsuarioNoExisteException("Usuario no encontrado: " + nickname);
    }
    if (nombre != null && !nombre.isBlank()) {
      usuarioModificar.setNombre(nombre);
    }
    if (imagen != null) {
      usuarioModificar.setImagen(imagen.isBlank() ? "PerfilSinFoto.jpg" : imagen);
    }
    
    if (usuarioModificar instanceof Organizador organizadorModificar) {
      if (descripcion != null) {
        organizadorModificar.setDescripcionGeneral(descripcion);
      }
      if (link != null) {
        organizadorModificar.setLinkSitioWeb(link);
      }
    } else if (usuarioModificar instanceof Asistente asistenteModificar) {
      if (apellido != null) {
        asistenteModificar.setApellido(apellido);
      }
      if (fechaNac != null) {
        asistenteModificar.setFechaNacimiento(fechaNac); 
      }
    }
  }
  
  /**
   * Obtiene un organizador según su nickname.
   *
   * @param nickname nombre del organizador
   * @return organizador correspondiente
   * @throws UsuarioNoExisteException si no existe el organizador
   */
  public DataUsuario getOrganizador(String nickname) throws UsuarioNoExisteException {
    Usuario usuarioBuscado = manejador.getOrganizador(nickname);
    if (usuarioBuscado == null) {
      throw new UsuarioNoExisteException("Organizador no encontrado: " + nickname);
    }
    return convertirDataUsuario(usuarioBuscado);
  }
  
  /**
   * Obtiene un asistente según su nickname.
   *
   * @param nickname nombre del organizador
   * @return organizador correspondiente
   * @throws UsuarioNoExisteException si no existe el organizador
   */
  public DataUsuario getAsistente(String nickname) throws UsuarioNoExisteException {
    Usuario asistente = manejador.getAsistente(nickname);
    if (asistente == null) {
      throw new UsuarioNoExisteException("Asistente no encontrado: " + nickname);
    }
    return convertirDataUsuario(asistente);
  }
  
  /**
   * Obtiene todos los usuarios registrados en el sistema.
   *
   * @return arreglo con los datos de los usuarios
   * @throws UsuarioNoExisteException si no hay usuarios registrados
   */
  public DataUsuario[] getUsuarios() throws UsuarioNoExisteException {
    Collection<Organizador> orgs = manejador.obtenerTodosOrganizadores();
    Collection<Asistente>  asis  = manejador.obtenerTodosAsistentes();

    if (orgs.isEmpty() && asis.isEmpty()) {
      throw new UsuarioNoExisteException("No hay usuarios registrados.");
    }
    List<DataUsuario> lista = new ArrayList<>(orgs.size() + asis.size());
    for (Organizador o : orgs) {
      lista.add(convertirDataUsuario(o));
    }
    for (Asistente  a : asis) {
      lista.add(convertirDataUsuario(a));
    }

    lista.sort(Comparator.comparing(DataUsuario::getNickname, String.CASE_INSENSITIVE_ORDER));
    return lista.toArray(new DataUsuario[0]);
  }
  
  /**
   * Obtiene todos los organizadores registrados en el sistema.
   *
   * @return arreglo con los datos de los organizadores
   * @throws UsuarioNoExisteException si no hay organizadores registrados
   */
  public DataUsuario[] getOrganizadores() throws UsuarioNoExisteException {
    Collection<Organizador> orgs = manejador.obtenerTodosOrganizadores();
    if (orgs.isEmpty()) {
      throw new UsuarioNoExisteException("No hay organizadores registrados.");
    }
    List<DataUsuario> lista = new ArrayList<>(orgs.size());
    for (Organizador o : orgs) { 
      lista.add(convertirDataUsuario(o));
    }
    lista.sort(Comparator.comparing(DataUsuario::getNickname));
    return lista.toArray(new DataUsuario[0]);
  }
  
  /**
   * Obtiene todos los asistentes registrados en el sistema.
   *
   * @return arreglo con los datos de los asistentes
   * @throws UsuarioNoExisteException si no hay asistentes registrados
   */
  public DataUsuario[] getAsistentes() throws UsuarioNoExisteException {
    Collection<Asistente> asis = manejador.obtenerTodosAsistentes();
    if (asis.isEmpty()) {
      throw new UsuarioNoExisteException("No hay asistentes registrados.");
    }
    List<DataUsuario> lista = new ArrayList<>(asis.size());
    for (Asistente a : asis) {
      lista.add(convertirDataUsuario(a));
    }
    lista.sort(Comparator.comparing(DataUsuario::getNickname));
    return lista.toArray(new DataUsuario[0]);
  }
  
  /**
   * Convierte un usuario del sistema en un DataUsuario.
   * Agrega los datos específicos según el tipo de usuario.
   *
   * @param u usuario a convertir
   * @return datos del usuario convertido
   */
  private DataUsuario convertirDataUsuario(Usuario usuario) {
    DataUsuario dataUsuario = new DataUsuario(usuario.getNickname(), usuario.getNombre(), 
        usuario.getCorreo(), usuario.getImagen(),
        (usuario instanceof Organizador) ? "Organizador" : "Asistente");
    if (usuario instanceof Organizador organizador) {
      dataUsuario.setDescripcion(organizador.getDescripcionGeneral());
      dataUsuario.setLink(organizador.getLinkSitioWeb());
    } else if (usuario instanceof Asistente asistente) {
      dataUsuario.setApellido(asistente.getApellido());
      dataUsuario.setFechaNacimiento(asistente.getFechaNacimiento());
    }
    return dataUsuario;
  }
  
  /**
   * Inicia sesión con las credenciales dadas.
   *
   * @param ident identificador del usuario (nickname o correo)
   * @param password contraseña del usuario
   * @return datos del usuario autenticado
   * @throws UsuarioNoExisteException si el usuario no existe
   * @throws PasswordIncorrectaException si la contraseña es incorrecta
   */
  @Override
  public DataUsuario login(String ident, String password) 
      throws UsuarioNoExisteException, PasswordIncorrectaException {
    if (ident == null || ident.isBlank()) {
      throw new UsuarioNoExisteException("Identificador vacío: ingrese nickname o correo");
    }
    if (password == null) {
      throw new PasswordIncorrectaException("Contraseña inválida (null)");
    }
    Usuario usuario = manejador.obtenerPorIdentificador(ident);
    if (usuario == null) {
      throw new UsuarioNoExisteException("No existe usuario con nickname/correo: " + ident.trim());
    }
    if (!usuario.verificarPassword(password)) {
      throw new PasswordIncorrectaException("Credenciales inválidas");
    }
    return convertirDataUsuario(usuario);
  }
  
  /**
   * Limpia todos los datos del manejador de usuarios.
   */
  public void limpar() {
    manejador.limpiar(); 
  }
  
  public void cambiarContrasenia(String nickname, String nuevaPass) {
    Usuario usuario = manejador.obtenerPorNickname(nickname);
    usuario.setPassword(nuevaPass);
  }
}