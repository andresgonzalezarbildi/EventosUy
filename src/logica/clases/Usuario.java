package logica.clases;

public class Usuario {

    private String nickname;
    private String nombre;
    private String correo;

    public Usuario(String nickname, String nombre, String correo) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
