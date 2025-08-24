package logica;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;

public interface IControladorUsuario {

    void altaUsuario(String nickname, String nombre, String correo, String tipo,
                     String descripcion, String link, String apellido, String fechaNac)
                     throws UsuarioRepetidoException;

    DataUsuario verInfoUsuario(String ni) throws UsuarioNoExisteException;

    DataUsuario[] getUsuarios() throws UsuarioNoExisteException;
}
