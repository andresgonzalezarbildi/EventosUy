package logica.clases;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa a un asistente dentro del sistema.
 * Contiene la información y funcionalidades asociadas
 * a los usuarios que participan en eventos.
*/
public class Asistente extends Usuario {
  private String apellido;
  private LocalDate fechaNacimiento;
  private Set<Registro> registros;
  
  /**
   * Constructor de un asistente.
   *
   * @param nickname        nickname del asistente
   * @param nombre          nombre del asistente
   * @param correo          correo del asistente
   * @param imagen          imagen del asistente
   * @param password        contraseña del asistente
   * @param apellido        apellido del asistente
   * @param fechaNacimiento fecha de nacimiento del asistente
   */
  public Asistente(String nickname, String nombre, String correo, String imagen, String password,
                   String apellido, LocalDate fechaNacimiento) {
    super(nickname, nombre, correo, imagen, password);
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.registros = new HashSet<>();
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }
  
  /**
   * Funcion que agrega el registro a una edicion al asistente.
   *
   * @param registro registro del asistente a una edicion
   */
  public void agregarRegistro(Registro registro) {
    registros.add(registro);
  }
}
