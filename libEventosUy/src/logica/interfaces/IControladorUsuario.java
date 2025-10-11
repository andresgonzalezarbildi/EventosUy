package logica.interfaces;

import java.time.LocalDate;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.clases.Organizador;
import logica.clases.Asistente;
import logica.clases.Usuario;
import excepciones.PasswordIncorrectaException;
import logica.datatypes.DataUsuario;

public interface IControladorUsuario {
	public void altaUsuario(String nickname, String nombre, String correo, String imagen, String password, String tipo, String descripcion, String link, String apellido, LocalDate fechaNac) throws UsuarioRepetidoException ;
	DataUsuario verInfoUsuario(String ni) throws UsuarioNoExisteException;
    DataUsuario[] getUsuarios() throws UsuarioNoExisteException;
    DataUsuario[] getOrganizadores();
    public DataUsuario[] getAsistentes();
    public void modificarUsuario(String nickname, String nombre, String descripcion, String imagen,String link, String apellido, LocalDate fechaNac) throws UsuarioNoExisteException;
    public Organizador getOrganizador(String nickname) throws UsuarioNoExisteException;
    public Asistente getAsistente(String nickname) throws UsuarioNoExisteException;
    public Usuario login(String nickname, String password) throws UsuarioNoExisteException, PasswordIncorrectaException;
}