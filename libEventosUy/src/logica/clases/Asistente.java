
package logica.clases;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Asistente extends Usuario {
    private String apellido;
    private LocalDate fechaNacimiento;
    private Set<Registro> registros;

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
    public void agregarRegistro(Registro r) {
        registros.add(r);
    }
}
