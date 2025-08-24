package logica;

public class DataUsuario {

    private String nickname;
    private String nombre;
    private String correo;

    public DataUsuario(String nickname, String nombre, String correo) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNickname() { return nickname; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }

    @Override
    public String toString() {
        return nickname + " (" + nombre + ", " + correo + ")";
    }
}
