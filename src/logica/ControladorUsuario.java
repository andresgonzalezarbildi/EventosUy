package logica;

import java.util.HashMap;
import java.util.Map;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;

public class ControladorUsuario implements IControladorUsuario {

    private Map<String, DataUsuario> usuarios;

    public ControladorUsuario() {
        usuarios = new HashMap<>();
    }

    @Override
    public void altaUsuario(String nickname, String nombre, String correo) throws UsuarioRepetidoException {
        if (usuarios.containsKey(nickname)) {
            throw new UsuarioRepetidoException("El usuario ya existe: " + nickname);
        }
        usuarios.put(nickname, new DataUsuario(nickname, nombre, correo));
    }

    @Override
    public DataUsuario verInfoUsuario(String nickname) throws UsuarioNoExisteException {
        if (!usuarios.containsKey(nickname)) {
            throw new UsuarioNoExisteException("Usuario no encontrado: " + nickname);
        }
        return usuarios.get(nickname);
    }

    @Override
    public DataUsuario[] getUsuarios() throws UsuarioNoExisteException {
        if (usuarios.isEmpty()) throw new UsuarioNoExisteException("No hay usuarios registrados.");
        return usuarios.values().toArray(new DataUsuario[0]);
    }
}
