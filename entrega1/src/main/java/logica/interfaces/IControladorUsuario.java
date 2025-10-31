package logica.interfaces;

import excepciones.PasswordIncorrectaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import java.time.LocalDate;
import logica.datatypes.DataUsuario;

/**
 * Define las operaciones principales para gestionar usuarios en el sistema.
 * Permite dar de alta, modificar, listar y autenticar usuarios.
 */
public interface IControladorUsuario {
  /**
   * Registra un nuevo usuario en el sistema.
   *
   * @param nickname     nombre único del usuario
   * @param nombre       nombre del usuario
   * @param correo       correo electrónico del usuario
   * @param imagen       imagen del usuario
   * @param password     contraseña del usuario
   * @param tipo         tipo de usuario (organizador o asistente)
   * @param descripcion  descripción del organizador (si aplica)
   * @param link         enlace del organizador (si aplica)
   * @param apellido     apellido del asistente (si aplica)
   * @param fechaNac     fecha de nacimiento del usuario
   * @throws UsuarioRepetidoException si el nickname o correo ya existen
   */
  public void altaUsuario(String nickname, String nombre, String correo, String imagen,
      String password, String tipo, String descripcion, String link, String apellido, 
      LocalDate fechaNac) throws UsuarioRepetidoException;

  /**
   * Devuelve la información de un usuario según su nickname.
   *
   * @param nickname nombre del usuario a consultar
   * @return datos del usuario
   * @throws UsuarioNoExisteException si el usuario no existe
   */
   public DataUsuario verInfoUsuario(String nickname) throws UsuarioNoExisteException;
  
  /**
   * Obtiene todos los usuarios registrados.
   *
   * @return arreglo con los datos de los usuarios
   * @throws UsuarioNoExisteException si no hay usuarios registrados
   */
  public DataUsuario[] getUsuarios() throws UsuarioNoExisteException;
  
  /**
   * Obtiene todos los organizadores registrados.
   *
   * @return arreglo con los datos de los organizadores
   * @throws UsuarioNoExisteException si no hay organizadores registrados
   */
  public DataUsuario[] getOrganizadores() throws UsuarioNoExisteException;
  
  /**
   * Obtiene todos los asistentes registrados.
   *
   * @return arreglo con los datos de los asistentes
   * @throws UsuarioNoExisteException si no hay asistentes registrados
   */
  public DataUsuario[] getAsistentes() throws UsuarioNoExisteException;
  
  /**
   * Modifica los datos de un usuario existente.
   *
   * @param nickname     nickname del usuario
   * @param nombre       nuevo nombre
   * @param descripcion  nueva descripción (si aplica)
   * @param imagen       nueva imagen
   * @param link         nuevo enlace (si aplica)
   * @param apellido     nuevo apellido (si aplica)
   * @param fechaNac     nueva fecha de nacimiento
   * @throws UsuarioNoExisteException si el usuario no existe
   */
  public void modificarUsuario(String nickname, String nombre, String descripcion, String imagen,
      String link, String apellido, LocalDate fechaNac) throws UsuarioNoExisteException;
  
  /**
   * Obtiene la información de un organizador según su nickname.
   *
   * @param nickname nombre del organizador
   * @return datos del organizador
   * @throws UsuarioNoExisteException si el organizador no existe
   */
  public DataUsuario getOrganizador(String nickname) throws UsuarioNoExisteException;
  
  /**
   * Obtiene la información de un asistente según su nickname.
   *
   * @param nickname nombre del asistente
   * @return datos del asistente
   * @throws UsuarioNoExisteException si el asistente no existe
   */
  public DataUsuario getAsistente(String nickname) throws UsuarioNoExisteException;
  
  /**
   * Inicia sesión con las credenciales dadas.
   *
   * @param ident    identificador del usuario (nickname o correo)
   * @param password contraseña del usuario
   * @return datos del usuario autenticado
   * @throws UsuarioNoExisteException    si el usuario no existe
   * @throws PasswordIncorrectaException si la contraseña es incorrecta
   */
  public DataUsuario login(String ident, String password) 
      throws UsuarioNoExisteException, PasswordIncorrectaException;
  
  /**
   * Inicia sesión con las credenciales dadas.
   *
   * @param ident    identificador del asistente (nickname o correo)
   * @param password contraseña del asistente
   * @return datos del asistente autenticado
   * @throws UsuarioNoExisteException    si el asistente no existe
   * @throws PasswordIncorrectaException si la contraseña es incorrecta
   */
  public DataUsuario loginMovil(String ident, String password) 
      throws UsuarioNoExisteException, PasswordIncorrectaException;
  
  public void cambiarContrasenia(String nickname, String nuevaPass);
    
}