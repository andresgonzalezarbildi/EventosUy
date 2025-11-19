package logica.datatypes;

import java.time.LocalDate;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataUsuario {

    private String nickname;
    private String nombre;
    private String correo;
    private String imagen;
    private String tipo;
    private String descripcion;
    private String link;
    private String apellido;

    @XmlJavaTypeAdapter(FormateoFecha.class)
    private LocalDate fechaNacimiento;

    public DataUsuario() {}

    public DataUsuario(String nickname, String nombre, String correo, String imagen, String tipo) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.correo = correo;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    @Override
    public String toString() { return nickname; }
}