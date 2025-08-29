
package logica.clases;
import java.util.Date;

public class Asistente extends Usuario {
    private String apellido;
    private Date fechaNacimiento;

    public Asistente(String nickname, String nombre, String correo,
                     String apellido, Date fechaNacimiento) {
        super(nickname, nombre, correo);
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
