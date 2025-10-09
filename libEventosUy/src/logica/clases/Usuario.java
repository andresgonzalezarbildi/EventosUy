package logica.clases;

public class Usuario {

    private String nickname;
    private String nombre;
    private String correo;
    private String imagen;
    private String password;

    public Usuario(String nickname, String nombre, String correo, String imagen, String password) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.correo = correo;
        setImagen(imagen);
        this.password = password;
    }
    
    public Usuario(String nickname, String nombre, String correo, String password) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.correo = correo;
        setImagen(null);
        this.password = password;
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
    
    public String getPassword() {
    	return password;
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
    
    
    public boolean verificarPassword(String passwordIngresada) {
        return this.password.equals(passwordIngresada);
    }
    
	public String getImagen() {
	    return (imagen == null || imagen.isBlank()) ? "PerfilSinFoto.png" : imagen;
	}
	
	public void setImagen(String imagen) {
	    this.imagen = (imagen == null || imagen.isBlank()) ? "PerfilSinFoto.png" : imagen;
	}
}
