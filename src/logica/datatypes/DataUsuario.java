package logica.datatypes;

public class DataUsuario {
    private String nickname;
    private String nombre;
    private String correo;

    // Campos opcionales según tipo
    private String tipo; // "Asistente" o "Organizador"
    private String descripcion;       // solo Organizador
    private String link;              // solo Organizador
    private String apellido;          // solo Asistente
    private String fechaNacimiento;   // solo Asistente

    // Constructor mínimo
    public DataUsuario(String nickname, String nombre, String correo, String tipo) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.correo = correo;
        this.tipo = tipo;
    }

    // Getters
    public String getNickname() { return nickname; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }

    public String getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }
    public String getLink() { return link; }
    public String getApellido() { return apellido; }
    public String getFechaNacimiento() { return fechaNacimiento; }

    // Setters para los campos opcionales
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setLink(String link) { this.link = link; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setTipo(String tipo) { this.tipo = tipo;}


    // ToString opcional para mostrar en JList
    @Override
    public String toString() {
        return nickname;
    }
}
